package com.nastyway.weekend.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CorsInterceptor implements HandlerInterceptor
{
    private static final Logger logger = LoggerFactory.getLogger(CorsInterceptor.class);
    
    private static final String  ACCESS_CONTROL_ALLOW_ORIGIN      = "Access-Control-Allow-Origin";
    private static final String  ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String  REQUEST_HEADER_ORIGIN            = "Origin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        logger.debug("preHandle() start");
        
        String origin = request.getHeader(REQUEST_HEADER_ORIGIN);
        logger.debug("Origin Header: {}", origin);

        // CORS �����ϵ��� ���� ��� �߰�
        if (StringUtils.hasLength(origin))
        {
            // ��û�� �����ο� ���� CORS �� ����Ѵ�. ������ �ʿ��ϴٸ� �ʿ��� ������ �����Ѵ�.
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            
            // credentials ���
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }
        
        logger.debug("preHandle() end");
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
