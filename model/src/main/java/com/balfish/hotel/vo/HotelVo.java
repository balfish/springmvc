package com.balfish.hotel.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class HotelVo {

    private Integer id;

    private Integer hotelId;

    private String hotelName;

    private String hotelAddress;

    private String hotelPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
