package com.balfish.common.utils.mobileNumber;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class MobileNumberUtils {

    // 手机号长度
    private static int MOBILE_NUMBER_LENGTH = 11;
    private static int MIN_MOBILE_NUMBER_LENGTH = 4;

    // 手机号格式
    private final static Pattern MOBILE_NUMBER_PATTERN = Pattern.compile("^(((\\+)|0|00)?86)?1\\d{2}(\\s)?\\d{4}(\\s)?\\d{4}$");

    /**
     * 校验手机号长度不为11位
     *
     * @param mobileNumber
     * @return true 合法;false 不合法
     */
    public static boolean checkMobileNumberLength(String mobileNumber) {

        return (StringUtils.length(mobileNumber) == MOBILE_NUMBER_LENGTH);
    }

    /**
     * 校验手机号是否合法
     * 注：只能校验国内手机号码，不支持国外手机号的校验
     *
     * @param mobileNumber
     * @return true 合法;false 不合法
     */
    public static boolean isMobileNumberValid(String mobileNumber) {
        if (StringUtils.isBlank(mobileNumber)) {
            return false;
        }
        return MOBILE_NUMBER_PATTERN.matcher(mobileNumber).matches();
    }

    /**
     * 给手机号打掩码 159****5718: 11位的中间4位掩码。非11位情况当长度<=4直接返回;其他情况之保留后4位；
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(final String mobile) {
        if (Strings.isNullOrEmpty(mobile)) {
            return StringUtils.EMPTY;
        }

        if (mobile.length() == MOBILE_NUMBER_LENGTH) {  //正常的11位
            int minU = Math.min(3, mobile.length());
            int maxL = Math.max(0, mobile.length() - 4);

            return mobile.substring(0, minU) + "****" + mobile.substring(maxL, mobile.length());
        }

        if (mobile.length() <= MIN_MOBILE_NUMBER_LENGTH) {    //长度<=4的情况
            return mobile;
        }

        //大于4且长度不等于11位的
        return Strings.repeat("*", mobile.length() - MIN_MOBILE_NUMBER_LENGTH)
                + mobile.substring(mobile.length() - MIN_MOBILE_NUMBER_LENGTH, mobile.length());
    }

}