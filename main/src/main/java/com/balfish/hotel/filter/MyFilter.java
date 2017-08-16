package com.balfish.hotel.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yhm on 2017/8/1 AM10:43.
 */
@Service("myFilter")
public class MyFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        LOGGER.info(httpServletRequest.getRequestURI());

        if (!httpServletRequest.getRequestURI().contains("monitor")) {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
        LOGGER.info(httpServletRequest.getRequestURI() + "after .....");
    }

    @Override
    public void destroy() {
        LOGGER.info("filter destroy");
    }
}
