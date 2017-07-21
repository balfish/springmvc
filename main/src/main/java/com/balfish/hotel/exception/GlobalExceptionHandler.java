package com.balfish.hotel.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    public static final String EXCEPTION_FOR_MONITOR_ATTRIBUTE = "exception_for_monitor_attribute";
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private MappingJackson2JsonView jsonView = new MappingJackson2JsonView();

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {

        // 这个是为了有全局处理时候照样可以跑出异常给handlerInterceptor
        httpServletRequest.setAttribute(EXCEPTION_FOR_MONITOR_ATTRIBUTE, e);

        LOGGER.error(e.getMessage(), e);
        if (!(handler instanceof HandlerMethod)) {
            ModelAndView mv = new ModelAndView("/error/500");
            return fillErrorMessage(mv, "error");
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ModelAndView mv;
        // 判断是否为json返回
        if (handlerMethod.getMethod().isAnnotationPresent(ResponseBody.class)) {
            mv = new ModelAndView(jsonView);
        } else {
            mv = new ModelAndView("/error/500");
        }
        return fillErrorMessage(mv, e.getMessage());
    }

    private ModelAndView fillErrorMessage(ModelAndView mv, String errmsg) {
        mv.addObject("ver", "1.0");
        mv.addObject("status", -1);
        mv.addObject("errmsg", errmsg);
        return mv;
    }

}
