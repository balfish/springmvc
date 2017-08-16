package com.balfish.hotel.train.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by yhm on 2017/8/2 AM10:31.
 */

@Component
public class ScheduleTask implements BeanPostProcessor, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);


    @Resource
    private SchedulingTaskExecutor commonExecutor;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        commonExecutor.submit(() -> LOGGER.info("task run ! beanName = {}!!!!!!!!!!!!!!!!", beanName));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("task finished! beanName = {}", beanName);
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("afterPropertiesSet~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
