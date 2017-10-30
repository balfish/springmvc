package com.balfish.common.enums;


import com.balfish.common.ScmException;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Method;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class EnumCodeUtils {

    /**
     * 一般的enum
     *
     * @param code  枚举码
     * @param clazz 　类
     * @param <T>   　泛型
     * @return 泛型
     */
    public static <T> T EnumCodeOf(int code, Class<T> clazz) {
        Preconditions.checkNotNull(code, "code不能为空");
        Preconditions.checkNotNull(clazz, "clazz不能为空");
        if (Enum.class.isAssignableFrom(clazz)) {
            throw new ScmException("{}不是enum类型", clazz.getSimpleName());
        }
        T[] enumConstants = clazz.getEnumConstants();
        if (ArrayUtils.isEmpty(enumConstants)) {
            return null;
        }
        for (T enumConstant : enumConstants) {
            try {
                Method getCode = clazz.getMethod("getCode");
                if (getCode != null && Objects.equal(code, getCode.invoke(enumConstant))) {
                    return enumConstant;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 实现了EnumTrait的枚举
     *
     * @param code  枚举码
     * @param clazz 　类
     * @param <T>   　泛型
     * @return 泛型
     */
    public static <T extends EnumTrait> T EnumTraitCodeOf(int code, Class<T> clazz) {
        Preconditions.checkNotNull(code, "code不能为空");
        Preconditions.checkNotNull(clazz, "clazz不能为空");
        if (!EnumTrait.class.isAssignableFrom(clazz)) {
            throw new ScmException("{}不是enumtrait类型", clazz.getSimpleName());
        }
        if (!Enum.class.isAssignableFrom(clazz)) {
            throw new ScmException("{}不是enum类型", clazz.getSimpleName());
        }
        T[] enumConstants = clazz.getEnumConstants();
        if (ArrayUtils.isEmpty(enumConstants)) {
            return null;
        }
        for (T enumConstant : enumConstants) {
            if (Objects.equal(code, enumConstant.getCode())) {
                return enumConstant;
            }
        }
        return null;
    }
}
