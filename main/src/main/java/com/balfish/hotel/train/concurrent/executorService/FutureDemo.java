package com.balfish.hotel.train.concurrent.executorService;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * <pre>
 *                       java.util.concurrent.ExecutorService
 *                       <T> Future<T> submit(Callable<T> task);
 *                       <T> Future<T> submit(Runnable task, T result);
 *                       Future<?> submit(Runnable task);
 *                       void execute(Runnable command);  (父类方法)
 *
 *                       在Future接口中声明了5个方法，下面依次解释每个方法的作用:
 *                       1 cancel()方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false.参数mayInterruptIfRunning表示是否允许取消(!*!正在执行却没有执行完毕!*!)的任务
 *                          a.如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false，即如果取消已经完成的任务会返回false；
 *                          b.如果任务正在执行，若mayInterruptIfRunning设置为true，则返回true，若mayInterruptIfRunning设置为false，则返回false；
 *                          c.如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true。
 *
 *                       2 isCancelled()方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true.
 *
 *                       3 isDone方法表示任务是否已经完成，若任务完成，则返回true.
 *
 *                       4 get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回.
 *
 *                       5 get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null.
 *
 *
 *                       Callable中的call()方法类似Runnable的run()方法，就是前者有返回值，后者没有
 *                       将Callable的对象传递给ExecutorService的submit方法，则该call方法自动在一个线程上执行，并且会返回执行结果Future对象。
 *                       将Runnable的对象传递给ExecutorService的submit方法，则该 run方法自动在一个线程上执行，并且会返回执行结果Future对象，但是在该Future对象上调用get方法，将返回null。
 *
 *
 *                       Java通过Executors提供四种线程池，分别为(@see java.util.concurrent.Executors):
 *                       newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *                       newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *                       newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 *                       newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 *
 *                 </pre>
 */
    /*
     *
     * RejectedExecutionHandler的四种拒绝策略
     *
     * ThreadPoolExecutor.AbortPolicy:
     * 当线程池中的数量等于最大线程数时抛出java.util.concurrent.RejectedExecutionException异常.
     * 涉及到该异常的任务也不会被执行.
     *
     * ThreadPoolExecutor.CallerRunsPolicy:
     * 当线程池中的数量等于最大线程数时,重试添加当前的任务;它会自动重复调用execute()方法
     *
     * ThreadPoolExecutor.DiscardOldestPolicy:
     * 当线程池中的数量等于最大线程数时,抛弃线程池中工作队列头部的任务(即等待时间最久Oldest的任务),并执行新传入的任务
     *
     * ThreadPoolExecutor.DiscardPolicy:
     * 当线程池中的数量等于最大线程数时,丢弃不能执行的新加任务
     */
public class FutureDemo {

    private final static int DEFAULT_THREAD_POOL_SIZE = Math.min(Runtime.getRuntime().availableProcessors() * 2, 8);

    private final static ExecutorService executorService = new ThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE,
            DEFAULT_THREAD_POOL_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    // 在jvm增加一个关闭的钩子，执行完这些hook后jvm才能关闭. 所以这些钩子可以在jvm关闭的时候进行内存清理，对象销毁等操作.
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(executorService::shutdown));
    }

    public static void main(String[] args) throws Exception {

        // java future
//        testFuture();

        // guava wrapped future
        testListenableFuture();
    }

    private static void testListenableFuture() throws Exception {

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        final ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("-------");
                TimeUnit.SECONDS.sleep(15);
                return 6;
            }
        });

        /** guava addListener */
        listenableFuture.addListener(new Runnable() {
            public void run() {
                try {
                    System.out.println("-------！！！！！！！");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("get listenable future's result with addListener " + listenableFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, listeningExecutorService);

        /** guava addCallback(对于addListener的包装) */
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            public void onSuccess(Integer integer) {
                System.out.println("get listenable future's result with addCallback " + integer);
            }

            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        System.out.println("--main thread finished!!--");
    }

    private static void testFuture() throws Exception {

        /** executorService.submit(Callable call) */
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                Thread.sleep(4000);
                return Integer.MIN_VALUE;
            }
        });

        /** executorService.submit(Runnable runnable) */
        List<Future> futureList = Lists.newArrayListWithCapacity(3);
        for (String ignored : new String[]{"1", "2", "3"}) {
            futureList.add(executorService.submit(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(Thread.currentThread().getName() + "thread invoke");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        /** FutureTask: Runnable和Future的子类 */
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(4000);
                return Integer.MAX_VALUE;
            }
        });
        executorService.execute(futureTask);

        // result
        System.out.println("主线程在执行任务");
        Thread.sleep(1000);
        System.out.println("executorService.submit(Callable call)运行结果" + future.get()); // blocking here, until this step return the result
        await(futureList);
        System.out.println("executorService.submit(Runnable runnable)运行结果:～～");
        System.out.println("futureTask运行结果" + futureTask.get()); // blocking here, until this step return the result
        System.out.println("所有任务执行完毕");
    }

    private static void await(List<Future> futureList) {
        for (Future future : futureList) {
            try {
                future.get();
            } catch (Exception e) {
                System.out.println("failed");
            }
        }
    }
}
