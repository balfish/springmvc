package com.balfish.hotel.interceptor;

import com.balfish.hotel.service.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class CookieInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieInterceptor.class);

    @Resource
    private CookieService cookieService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("------------------preHandle");
        if (cookieService.isLogin(request)) {
            return true;
        }
        if (request.getRequestURI().startsWith("/homePage")) {
            LOGGER.info("execute preHandle method, intercept the url startsWith /homePage");
            response.sendRedirect("/cookie/login");
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("------------------postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("------------------afterCompletion");

    }
}
