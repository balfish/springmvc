package com.balfish.hotel.train.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by yhm on 2017/8/1 PM2:18.
 * <p>
 * xml注解示例
 * <p>
 * <pre>
 *     execution语法:
 *     任意公共方法的执行:
 *        execution(public * *(..))
 *
 *     任意一个以get开头的方法的执行
 *        execution(* get*(..))
 *
 *     HotelService类的任意方法
 *        execution(* com.balfish.hotel.service.HotelService.*(..))
 *
 *     service包里的任意方法的执行
 *        execution(* com.balfish.hotel.service.*.*(..))
 *
 *     service包和所有子包里的任意类的任意方法的执行
 *        execution(* com.balfish.hotel.service..*.*(..)) </pre>
 * <pre>
 *     结果:
 *     INFO|2017-08-01 14:58:24|AspectDemo            |aspect 已经生效喽
 * INFO|2017-08-01 14:58:24|AspectAnnoDemo             |aspect 注解 前置通知已经生效喽execution(String com.balfish.hotel.controller.HotelController.test())
 * INFO|2017-08-01 14:58:24|HotelController            |http://localhost:8080/hotel/test...
 * INFO|2017-08-01 14:58:24|AspectAnnoDemo             |around execution(String com.balfish.hotel.controller.HotelController.test())	Use time : 18 ms!
 * INFO|2017-08-01 14:58:24|AspectAnnoDemo             |aspect 注解 后置通知已经生效喽execution(String com.balfish.hotel.controller.HotelController.test())
 * INFO|2017-08-01 14:58:24|AspectAnnoDemo             |afterReturn execution(String com.balfish.hotel.controller.HotelController.test())</pre>
 */
@Component
@Aspect
public class AspectAnnoDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectAnnoDemo.class);

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.balfish.hotel.controller.HotelController.test(..))")
    public void pointcut() {
    }

    /*
     * 配置前置通知,使用在方法aspect()上注册的切入点
	 * 同时接受JoinPoint切入点对象,可以没有该参数
	 */
    @Before(value = "pointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("aspect 注解 前置通知已经生效喽" + joinPoint);
    }


    //配置后置通知,使用在方法aspect()上注册的切入点
    @After(value = "pointcut()")
    public void afterAdvice(JoinPoint joinPoint) {
        LOGGER.info("aspect 注解 后置通知已经生效喽" + joinPoint);
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("pointcut()")
    public void around(JoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            }
        }
    }

    //配置后置返回通知,使用在方法aspect()上注册的切入点
    @AfterReturning("pointcut()")
    public void afterReturn(JoinPoint joinPoint) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("afterReturn " + joinPoint);
        }
    }

    //配置抛出异常后通知,使用在方法aspect()上注册的切入点
    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
        }
    }
}
