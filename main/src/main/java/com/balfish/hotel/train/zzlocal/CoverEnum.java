package com.balfish.hotel.train.zzlocal;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public enum CoverEnum {

    COVER(0, "完全覆盖"),
    PARTIAL_COVER(1, "部分覆盖"),
    NOT_COVER(2, "不覆盖");

    private int code;
    private String desc;

    CoverEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
