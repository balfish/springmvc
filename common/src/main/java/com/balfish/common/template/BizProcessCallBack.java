package com.balfish.common.template;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public abstract class BizProcessCallBack {

    /**
     * 参数检查
     */
    void checkParams() {
    }

    /**
     * 执行待处理操作，比如模型的创建，修改，删除等
     */
    public abstract <T> T process();

    /**
     * 执行成功的监控
     *
     * @param execTime 执行时长
     */
    public void succMonitor(long execTime) {
    }

    /**
     * 执行失败的监控
     */
    public void failMonitor() {
    }

    /**
     * finally中调用方法
     */
    public void afterProcess() {
    }
}
