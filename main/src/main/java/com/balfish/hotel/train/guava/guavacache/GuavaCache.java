package com.balfish.hotel.train.guava.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * <p/>
 * 缓存的主要作用是暂时在内存中保存业务系统的数据处理结果，并且等待下次访问使用。
 * 在日常开发的很多场合，由于受限于硬盘IO的性能或者我们自身业务系统的数据处理和获取可能非常费时，
 * 当我们发现我们的系统这个数据请求量很大的时候，频繁的IO和频繁的逻辑处理会导致硬盘和CPU资源的瓶颈出现。
 * 缓存的作用就是将这些来自不易的数据保存在内存中，当有其他线程或者客户端需要查询相同的数据资源时，直接从缓存的内存块中返回数据，
 * 这样不但可以提高系统的响应时间，同时也可以节省对这些数据的处理流程的资源消耗，整体上来说，系统性能会有大大的提升。
 * <p/>
 * Guava Cache是一个全内存的本地缓存实现，它提供了线程安全的实现机制。整体上来说Guava cache 是本地缓存的不二之选，简单易用，性能好
 * <p/>
 * Guava Cache有两种创建方式：1. cacheLoader 2. callable callback
 * <p/>
 * LoadingCache是Cache的封装,提供了V get(K key)方法, 包装了原始的Cache的 V get(K key, Callable<? extends V> valueLoader)方法
 */
public class GuavaCache {

    /*
     * 不需要延迟处理,泛型抽象
     */
    private static <K, V> LoadingCache<K, V> loadingCache(CacheLoader<K, V> cacheLoader) {
        return CacheBuilder.newBuilder().initialCapacity(100).maximumSize(10).expireAfterWrite(1, TimeUnit.HOURS)
                .build(cacheLoader);
    }

    /*
     * 封装成LoadingCache,通过 V get(K key) throws ExecutionException 调用
     */
    private static LoadingCache<String, String> getLoadingCache() throws Exception {
        return loadingCache(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return "default";
            }
        });
    }

    private static Cache<String, String> cache = callableCache();

    /*
     * 需要延迟处理,泛型抽象
     */
    private static <K, V> Cache<K, V> callableCache() {
        return CacheBuilder.newBuilder().initialCapacity(100).maximumSize(10).expireAfterWrite(1, TimeUnit.HOURS)
                .build();
    }

    /*
     * 封装成Cache,通过 V get(K key, Callable<? extends V> valueLoader) 调用
     */
    @SuppressWarnings("all")
    private static Object getCallableCache(final String key) throws Exception {
        return cache.get(key, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return StringUtils.isNoneBlank(key) ? key : "default";
            }
        });
    }

    public static void main(String[] args) throws Exception {
        testLoadingCache();
        System.out.println("---------------------------------------------------------");
        testCallableCache();
    }

    private static void testLoadingCache() throws Exception {
        LoadingCache<String, String> guavaCache = getLoadingCache();
        // 通过load加载 此时size=1 key1->defaultValue
        System.out.println(guavaCache.get("key1"));
        System.out.println(guavaCache.size());

        // 直接放入缓存，不经过load方法加载 此时size=1 key1->value1
        guavaCache.put("key1", "value1");
        System.out.println(guavaCache.get("key1") + ", the cache size is " + guavaCache.size());
        // 通过load加载 此时size=2 key2->defaultValue 而且因为一直是获取同一个key（key2）,并不重新load进cache,故size一直不增加
        System.out.println(guavaCache.get("key2") + ", the cache size is " + guavaCache.size());
        System.out.println(guavaCache.get("key2") + ", the cache size is " + guavaCache.size());

        // 刷新缓存, 通过load加载 刷新后只是清洗了指定key的value,size并没有变化
        System.out.println("before fresh, the cache size is " + guavaCache.size());
        System.out.println(guavaCache.get("key1") + ", the cache size is " + guavaCache.size());
        guavaCache.refresh("key1");
        System.out.println("after fresh, the cache size is " + guavaCache.size());
        System.out.println(guavaCache.get("key1") + ", the cache size is " + guavaCache.size());

        // 通过load加载新的key3，此时size=3
        System.out.println(guavaCache.get("key3"));
        System.out.println("the cache size is " + guavaCache.size());
    }

    private static void testCallableCache() throws Exception {
        System.out.println(getCallableCache("lisa"));
        System.out.println(getCallableCache("jerry"));
        System.out.println(getCallableCache("lisa"));
    }
}
