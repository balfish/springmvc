package com.balfish.common.utils.properties;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class PropertyUtils {

    private static final String DEFAULT_PROPERTY_FILE = "xx.properties";

    public static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);

    private static TreeMap<String, String> props = Maps.newTreeMap();

    static {
        load(DEFAULT_PROPERTY_FILE);
    }

    /**
     * 加载指定文件
     * <p>
     * 如果要加载其他资源文件可以单独调用这个方法
     *
     * @param propertiesFileName 资源文件名称
     */
    private static void load(String propertiesFileName) {
        Properties properties = readPropertiesFile(propertiesFileName);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            props.put((String) entry.getKey(), (String) entry.getValue());
        }
        overrideProperties();
    }

    /**
     * 如果和系统属性一致, 用系统属性覆盖
     */
    private static void overrideProperties() {
        Enumeration<String> enumeration = Collections.enumeration(props.keySet());
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = System.getProperty(name);
            if (value != null) {
                props.put(name, value);
            }
        }
    }

    /**
     * 获取当前容器的类加载器, 读取WEB-INF/classes下的属性文件 或使用主线程类加载器
     *
     * @param propertiesFileName 资源文件名称
     * @return Properties
     */
    private static Properties readPropertiesFile(String propertiesFileName) {
        Properties properties = new Properties();

        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(propertiesFileName);
        if (inputStream == null) {
            inputStream = PropertyUtils.class.getResourceAsStream("/" + propertiesFileName);
        }
        Preconditions.checkNotNull(inputStream, "资源文件不存在");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("加载配置文件失败", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.error("关闭流失败", e);
            }
        }
        return properties;
    }

    /**
     * 根据指定key获得value
     *
     * @param key          key
     * @param defaultValue defaultValue
     * @return value
     */
    public static String getValueByKey(String key, String defaultValue) {
        return props.get(key) == null ? defaultValue : props.get(key);
    }

    /**
     * 获取所有key的枚举
     *
     * @return Enumeration
     */
    public static Enumeration<String> getKeys() {
        return Collections.enumeration(props.keySet());
    }

    public static void main(String[] args) {

        List list = Arrays.asList(1, 2, 3, 4);
        Enumeration enumeration = Collections.enumeration(list);
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }

        System.out.println(getValueByKey("classA", "xx"));
    }
}
