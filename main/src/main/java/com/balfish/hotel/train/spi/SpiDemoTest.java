package com.balfish.hotel.train.spi;

import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class SpiDemoTest {
    /**
     * java spi机制（service provider interface） 系统抽象的不同模块中,往往有很多的实现方案，比如日志模块的方案，xml解析模块，jdbc模块的方案等
     * <p>
     * 面向对象的设计里，一般推荐模块之间基于接口编程，模块之间不对实现类进行硬编码，一旦代理里涉及具体的实现类就违反了可插拔的原则。 如果要替换一种实现时，就需要修改代码
     * <p>
     * 为了实现在模块装配的时候不在程序里动态指明，这就需要一种服务发现机制。java spi就是提供这样的一个机制：为某个接口寻找服务实现的机制 有点类似IOC 的思想，就是将装配的控制权移到程序之外，在模块化涉及中这个机制尤其重要
     * <p>
     * <p>
     * <p>
     * java spi的具体约定如下 ：
     * <p>
     * 当服务的提供者，提供了服务接口的一种实现之后，在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。该文件里就是实现该服务接口的具体实现类。而当外部程序装配这个模块的时候，
     * 就能通过该jar包META-INF/services/里的配置文件找到具体的实现类名，并装载实例化，完成模块的注入。
     * <p>
     * 基于这样一个约定就能很好的找到服务接口的实现类，而不需要再代码里制定。
     * <p>
     * jdk提供服务实现查找的一个工具类：java.util.ServiceLoader
     */

    public static void main(String[] args) {
        Iterator it = Service.providers(SpiService.class);

        while (it.hasNext()) {
            SpiService spiService = (SpiService) it.next();
            spiService.test();
        }
        System.out.println("--------------------------------------------");

        ServiceLoader<SpiService> loader = ServiceLoader.load(SpiService.class);

        for (SpiService spiService : loader) {
            spiService.test();
        }
    }
}
