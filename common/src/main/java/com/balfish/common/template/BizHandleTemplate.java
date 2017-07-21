package com.balfish.common.template;

import com.balfish.common.ScmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class BizHandleTemplate {

    private static final Logger logger = LoggerFactory.getLogger(BizHandleTemplate.class);

    /**
     * 没有result的模板方法
     *
     * @param action 操作回调接口
     */
    public static void executeNoResult(BizProcessCallBackNoResult action) {

        long startTime = System.currentTimeMillis();

        try {

            // 参数校验
            {
                action.checkParams();
            }

            // 执行业务操作
            {
                action.process();
            }

            // 监控成功结果
            {
                action.succMonitor(System.currentTimeMillis() - startTime);
            }
        } catch (ScmException e) {
            // 监控失败结果
            {
                action.failMonitor();
            }

            // 增加监控

            logger.error("系统异常! error", e);
            throw e;
        } catch (Exception e1) {
            // 监控失败结果
            {
                action.failMonitor();
            }

            // 增加监控

            logger.error("系统未知异常! e: " + e1.getMessage(), e1);
            throw new ScmException("SYSTEM_EXCEPTION", e1);
        } finally {

            try {

                {
                    action.afterProcess();
                }

            } catch (Exception e) {
                logger.error("finally中调用方法出现异常！e:" + e.getMessage(), e);
            }
        }
    }

    /**
     * 有result的模板方法
     *
     * @param action 操作回调接口
     */
    public static <T> T execute(BizProcessCallBack action) {

        T result;

        long startTime = System.currentTimeMillis();

        try {

            // 参数校验
            {
                action.checkParams();
            }

            // 执行业务操作
            {
                result = (T) action.process();
            }

            // 监控成功结果
            {
                action.succMonitor(System.currentTimeMillis() - startTime);
            }
        } catch (ScmException e) {
            // 监控失败结果
            {
                action.failMonitor();
            }

            // 增加监控

            logger.error("系统异常! errorMsg: {} ", e);
            throw e;
        } catch (Exception e1) {
            // 监控失败结果
            {
                action.failMonitor();
            }

            // 增加监控

            logger.error("系统未知异常! e: " + e1.getMessage(), e1);
            throw new ScmException("SYSTEM_EXCEPTION", e1);
        } finally {
            try {

                {
                    action.afterProcess();
                }

            } catch (Exception e) {
                logger.error("finally中调用方法出现异常！e:" + e.getMessage(), e);
            }

        }
        return result;
    }
}
