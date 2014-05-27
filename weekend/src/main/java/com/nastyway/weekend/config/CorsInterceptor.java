package com.nastyway.weekend.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nastyway.weekend.login.model.Login;

public class CorsInterceptor implements HandlerInterceptor
{
    private static final Logger logger = LoggerFactory.getLogger(CorsInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("preHandle() start");
        
        String rootPath = request.getContextPath()+"/login/loginForm.do";
        
        //�α��� ���� ����üũ���� ��������.
        String[] requestURI = request.getRequestURI().split("/");
        String loginPath = request.getContextPath()+"/login";
        
        if(requestURI.length>2) {
	        if(loginPath.equals("/"+requestURI[1]+"/"+requestURI[2])){
	        	return true;
	        }
        }
        
		HttpSession session = request.getSession(false);

		if (session == null) {
			
			response.sendRedirect(rootPath);
			// index.jsp �� �̵�, web.xml �� ���� �Ǿ� ���� (<welcome-file-list> �±�)
			return false;
		} else {
			
			Login login = (Login) session.getAttribute("userInfo");
			// UserInfo �� ���� ���
			if (login != null && login.getUserId() != null) {
				// session exist
			} else {
				// session non exist
				response.sendRedirect(rootPath);
				return false;
			}
		}

		System.out.println("preHandle() end");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        // do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        // do nothing
    }

}
