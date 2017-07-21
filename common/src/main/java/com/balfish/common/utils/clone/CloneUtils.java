package com.balfish.common.utils.clone;

import com.balfish.common.utils.json.JsonUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
@SuppressWarnings("unchecked")
public class CloneUtils {

    /**
     * 浅拷贝
     */
    public static <T> T shadowClone(T t) {
        try {
            if (t == null) {
                return null;
            }

            Class<? extends T> clazz = (Class<? extends T>) t.getClass();
            T instance = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();
            if (fields != null) {
                for (Field field : fields) {
                    int modifiers = field.getModifiers();
                    if (Modifier.isFinal(modifiers) || Modifier.isStatic(modifiers)) { //final的无法拷贝, static的无需拷贝
                        continue;
                    }
                    field.setAccessible(true);
                    field.set(instance, field.get(t));
                }
            }
            return instance;
        } catch (Exception e) {
            System.out.printf("error" + e);
            // ignore
        }
        return null;
    }

    /**
     * 深拷贝
     */
    public static <T> T deepClone(T t, Class<T> clazz) throws IOException {
        try {
            if (t == null || clazz == null) {
                return null;
            }
            return JsonUtils.toBean(JsonUtils.toJson(t), clazz);
        } catch (Exception e) {
            System.out.printf("error" + e);
            // ignore
        }
        return null;
    }
}
