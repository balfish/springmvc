package com.balfish.hotel.train.proxy;

import com.google.common.collect.Maps;

import java.lang.reflect.*;
import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * 动态代理的作用是什么:
 * <p/>
 * 1.Proxy类的代码量被固定下来，不会因为业务的逐渐庞大而庞大；
 * 2.可以实现AOP编程，实际上静态代理也可以实现，总的来说，AOP可以算作是代理模式的一个典型应用；
 * 3.解耦，通过参数就可以判断真实类，不需要事先实例化，更加灵活多变。
 *
 * @see https://www.zhihu.com/question/20794107/answer/23334315
 */
public class ProxyHandler implements InvocationHandler {
    private Object target;

    public Object bind(Object target) {
        this.target = target;

        System.out.println("----" + target.getClass().getName());
        System.out.println("----" + target.getClass().getInterfaces()[0]);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeMethod();
        return method.invoke(target, args);
    }

    private void beforeMethod() {
        System.out.println("before method...");
    }
}


    //    private Map<String, Object> cache = Maps.newHashMap();

//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        beforeMethod();
//        /* 考虑以下各种情况，有多个提供类，每个类都有getXxx(String name)方法，每个类都要加入缓存功能，
//         使用静态代理虽然也能实现，但是也是略显繁琐，需要手动一一创建代理类, 用下面的方法 */
//        Type[] types = method.getParameterTypes();
//        if (method.getName().matches("get.+") && (types.length == 1) && (types[0] == String.class)) {
//            String key = (String) args[0];
//            Object value = cache.get(key);
//            if (value == null) {
//                value = method.invoke(target, args);
//                cache.put(key, value);
//            }
//            return value;
//        }
//        afterMethod();
//        return method.invoke(target, args);
//    }
//}
