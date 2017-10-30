package com.balfish.hotel.enums;

import com.balfish.common.enums.EnumTrait;

import java.util.Arrays;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public enum HotelGradeType implements EnumTrait {
    STAR_ONE(1, "一星"),
    STAR_TWO(2, "二星"),
    STAR_THREE(3, "三星");

    private int code;
    private String text;

    HotelGradeType(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static HotelGradeType codeOf(int code) {
        return Arrays.stream(HotelGradeType.values()).filter(h -> h.getCode() == code).findAny().orElse(null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
