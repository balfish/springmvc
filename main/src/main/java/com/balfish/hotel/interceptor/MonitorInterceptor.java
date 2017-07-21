package com.balfish.hotel.interceptor;

import com.balfish.hotel.exception.GlobalExceptionHandler;
import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * 响应时间拦截器  HandlerInterceptorAdapter -> AsyncHandlerInterceptor -> HandlerInterceptor
 * <p>
 * 也可使用 threadLocal代替request, 都是线程本地变量
 */
public class MonitorInterceptor extends HandlerInterceptorAdapter {

    // private ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    private static final String START_MILLIS = "startMillis";

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorInterceptor.class);

    enum MonitorResult {
        SUCC,
        FAIL
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long startMillis = System.currentTimeMillis();
        request.setAttribute(START_MILLIS, startMillis);
        // threadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        Long startMillis = (Long) request.getAttribute(START_MILLIS);
        // Long aLong = threadLocal.get();
        if (startMillis == null || !(handler instanceof HandlerMethod)) {
            return;
        }
        long elapsedMillis = System.currentTimeMillis() - startMillis;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> clazz = handlerMethod.getBeanType();
        String methodName = handlerMethod.getMethod().getName();

        Monitor monitor = handlerMethod.getMethodAnnotation(Monitor.class);

        Exception exception = (Exception) request.getAttribute(GlobalExceptionHandler.EXCEPTION_FOR_MONITOR_ATTRIBUTE);
        if (ex == null && exception == null) {
            monitorOnSucc(clazz, methodName, monitor, elapsedMillis);
        } else {
            monitorOnFail(clazz, methodName, monitor, ex == null ? exception : ex);
        }
        // threadLocal.remove();
    }

    private void monitorOnSucc(Class<?> clazz, String methodName, Monitor monitor, long elapsedMillis) {
        Preconditions.checkNotNull(clazz, "clazz参数不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(methodName), "methodName参数不能为空");
        String metric;
        if (monitor != null && StringUtils.isNotBlank(monitor.onSucc())) {
            metric = monitor.onSucc();
        } else {
            metric = fetchMetric(clazz, methodName, MonitorResult.SUCC);
        }
        LOGGER.info("成功, 报警名称为{}, 响应时间为{}", metric, elapsedMillis);
    }

    private void monitorOnFail(Class<?> clazz, String methodName, Monitor monitor, Exception ex) {
        Preconditions.checkNotNull(clazz, "clazz参数不能为空");
        Preconditions.checkArgument(StringUtils.isNotBlank(methodName), "methodName参数不能为空");
        String metric;
        if (monitor != null && StringUtils.isNotBlank(monitor.onFail())) {
            metric = monitor.onFail();
        } else {
            metric = fetchMetric(clazz, methodName, MonitorResult.FAIL);
        }
        LOGGER.info("失败, 报警名称为{}, 异常为", metric, ex);
    }

    private String fetchMetric(Class<?> clazz, String methodName, MonitorResult result) {
        String className = clazz.getSimpleName();
        if (StringUtils.isBlank(className) || StringUtils.isBlank(methodName)) {
            return StringUtils.EMPTY;
        }
        className = StringUtils.removeEnd(className, "Controller");
        className = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, className);
        methodName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, methodName);
        String metric;
        String baseName = className + "_" + methodName;
        if (result == MonitorResult.SUCC) {
            metric = baseName + "_succ";
        } else {
            metric = baseName + "_fail";
        }
        return metric;
    }
}
