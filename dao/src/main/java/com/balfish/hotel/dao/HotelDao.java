package com.balfish.hotel.dao;

import com.balfish.hotel.model.HotelEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
@Repository
public interface HotelDao {
    List<HotelEntity> query(@Param("id") Integer id);

    void add(HotelEntity hotelEntity);
}
