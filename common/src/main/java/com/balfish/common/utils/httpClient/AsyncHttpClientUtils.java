package com.balfish.common.utils.httpClient;

import com.balfish.common.utils.properties.PropertyUtils;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
class AsyncHttpClientUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncHttpClientUtils.class);

    /**
     * 默认线程数，2倍cup核心数
     */
    private final static int DEFAULT_THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 配置的处理http请求的线程池大小
     */
    private final static int THREAD_POOL_SIZE = NumberUtils.toInt(PropertyUtils.getValueByKey("http_thread_pool_size", null), DEFAULT_THREAD_POOL_SIZE);

    private final AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();

    private final static Supplier<AsyncHttpClient> supplier = Suppliers.memoize(new Supplier<AsyncHttpClient>() {
        public AsyncHttpClient get() {
            return new AsyncHttpClient();
        }
    });
}
