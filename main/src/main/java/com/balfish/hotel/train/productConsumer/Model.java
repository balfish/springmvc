package com.balfish.hotel.train.productConsumer;

/**
 * Created by yhm on 2017/11/6 PM3:06.
 * <p>
  add  : 若队列已满，抛出IllegalStateException异常。
  offer: 若队列已满，返回false
  put  : 若队列已满，发生阻塞，等待元素消费。(LinkedBlockingQueue 特有，非父接口Queue方法)
 * <p>
  remove: 若队列为空，抛出NoSuchElementException异常。
  poll  : 若队列为空，返回null。
  take  : 若队列为空，发生阻塞，等待元素进入。(LinkedBlockingQueue 特有，非父接口Queue方法)
 */
public interface Model {
    Runnable newRunnableConsumer();

    Runnable newRunnableProducer();


}
