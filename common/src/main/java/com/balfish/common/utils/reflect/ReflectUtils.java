package com.balfish.common.utils.reflect;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class ReflectUtils {

    public static Map<String, Object> getReflectMap(Object object) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

}
