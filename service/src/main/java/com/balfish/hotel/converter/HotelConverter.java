package com.balfish.hotel.converter;

import com.balfish.hotel.model.HotelEntity;
import com.balfish.hotel.vo.HotelVo;
import org.springframework.beans.BeanUtils;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

public class HotelConverter {

    public static HotelEntity vo2Entity(HotelVo hotelVo) {
        HotelEntity hotelEntity = new HotelEntity();
        BeanUtils.copyProperties(hotelVo, hotelEntity);
        return hotelEntity;
    }

    public static void main(String[] args) {
        HotelVo hotelVo = new HotelVo();
        hotelVo.setId(12);
        hotelVo.setHotelAddress("xxx");
        hotelVo.setHotelName("aaa");

        HotelEntity hotelEntity = vo2Entity(hotelVo);
        System.out.println(hotelEntity);
    }

}
