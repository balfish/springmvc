package com.balfish.hotel.train.java.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class DaemonDemo {

    /**
     * <pre>
     * Java中有两类线程：User Thread(用户线程)、Daemon Thread(守护线程):
     * (1) Daemon的作用是为其他线程的运行提供便利服务, 比如gc线程就是一个很称职的守护者.
     * (2) User和Daemon两者几乎没有区别, 唯一的不同之处就在于虚拟机的离开: 如果 User Thread已经全部退出运行了, 只剩下Daemon Thread存在了, 虚拟机也就退出了.
     * (3) 守护线程并非只有虚拟机内部提供, 用户在编写程序时也可以自己设置守护线程. 通过 thread.setDaemon(true).
     *
     * 这里有几点需要注意：
     * (1) thread.setDaemon(true)必须在thread.start()之前设置, 否则会抛出一个IllegalThreadStateException异常.你不能把正在运行的常规线程设置为守护线程.
     * (2) 在Daemon线程中产生的新线程也是Daemon的.
     * (3) 不要认为所有的应用都可以分配给Daemon来进行服务, 比如读写操作或者计算逻辑.因为你不可能知道在所有的User完成之前, Daemon是否已经完成了预期的服务任务.
     *     一旦所有User Thread离开了, 虚拟机也就退出运行了, 可能大量数据还没有来得及读入或写出, 计算任务也可能多次运行结果不一样.
     * </pre>
     */

    public static void main(String[] args) {
        Runnable r = new MyRunnable();
        Thread thread = new Thread(r);
        /** 下面这句如果不注释掉的话, 主线程早早结束, 不会打印输出语句了 */
        thread.setDaemon(true);
        thread.start();
    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主进程结束了, 这个(耗时的)操作被淹没在了历史的洪流中");
        }
    }
}
