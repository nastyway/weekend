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
    	logger.debug("Client IP : "+request.getRemoteHost()+", requested URL : "+request.getRequestURL());
        String rootPath = request.getContextPath()+"/login/loginForm.do";
        
        //로그인 쪽은 세션체크에서 배제하자.
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
			return false;
		} else {
			
			Login login = (Login) session.getAttribute("userInfo");
			// UserInfo 로 세션 등록
			if (login != null && login.getUserId() != null) {
				// session exist
			} else {
				// session non exist
				response.sendRedirect(rootPath);
				return false;
			}
		}

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
