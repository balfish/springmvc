package com.balfish.hotel.service;

import com.balfish.hotel.dao.HotelDao;
import com.balfish.hotel.model.HotelEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
@Service
public class HotelService {

    @Resource
    private HotelDao hotelDao;

    public List<HotelEntity> query(Integer id) {
        return hotelDao.query(id);
    }

    public void add(HotelEntity hotelEntity) {
        hotelDao.add(hotelEntity);
    }
}
