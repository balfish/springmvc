package com.balfish.common.utils.json;

import com.balfish.common.ApiResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 接受JSON中存在注释
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 接受JSON中不使用双引号包围Key
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 接受JSON中用单引号代替双引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 接受JSON中存在控制字符在非字符串中的情况
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        // 反序列化中忽略未知JSON字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 不序列化Map中值为null的数据
        objectMapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        // 不序列化为null的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());
        module.addSerializer(Date.class, new FDateJsonSerializer());
        module.addDeserializer(Date.class, new FDateJsonDeserializer());
        objectMapper.registerModule(module);
    }

    public static <T> String toJson(T t) {
        try {
            if (t == null) {
                return "";
            }
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toBean(String json, Class<T> cls) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化集合类型(List,Set,Map等泛型类)
     * <pre>
     *  如: List<Placeholder> placeholders = JsonUtil.toBean(content, ArrayList.class, Placeholder.class);
     * </pre>
     */
    public static <T> T toBean(String json, Class<?> parametrized, Class<?>... parameterClasses) {
        try {
            // 下面这个也行, 只不过已经被废弃了
            // JavaType type1 = objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
            JavaType type = objectMapper.getTypeFactory().constructParametrizedType(parametrized, parametrized,
                    parameterClasses);

            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String xx = toJson(ApiResult.buildSuccessResult(new User("xx", 12)));
        System.out.println(xx);


        HashMap<Long, Integer> map = Maps.newHashMap();
        map.put(111L, 1);
        map.put(222L, 2);
        map.put(333L, 3);
        String s = JsonUtils.toJson(map);
        System.out.println(s);


        Object o = JsonUtils.toBean(s, HashMap.class, Long.class, Integer.class);

        System.out.println(o);
    }
}


class User {
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}