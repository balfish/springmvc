package com.balfish.hotel.train.java.jvm;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p>
 * 虚拟机启动参数: -Xmn10m -Xms40m -Xmx40m
 * a. Xms 表示分配的最小堆内存 xms是extended memory_start的缩写
 * b. Xmx 表示分配的最大堆内存 xmx是extended memory_max的缩写
 * c. Xmn 表示分给新生代的内存 extended memory_new的缩写 (xss是extended thread stack size的缩写)
 * <p>
 * <p>
 * -verbose:gc -Xms20m -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+UseSerialGC
 * Xms20m表示分配的最小堆内存，Xmx20M表示最大堆内存 , Xmn10m表示分给新生代的内存为10m,
 * SurvivorRatio表示新生代的eden和from与to区域的内存占比,PrintGCDetails表示打印垃圾收集的日志信息 , UseSerialGC表示新生代使用SerialGC收集器.
 * <p>
 * <p>
 * 调优总结： http://unixboy.iteye.com/blog/174173 Java内存与垃圾回收详解：
 * http://colobu.com/2014/12/16/java-jvm-memory-model-and-garbage-collection-monitoring-tuning/ 写的特别好！！ jps jstat
 * jstack jinfo jmap http://zhangyiqian.iteye.com/blog/1387749
 */
public class JVMTools {
    public static void main(String[] args) {

    }

}
