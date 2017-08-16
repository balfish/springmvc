package com.balfish.hotel.biz;

import com.balfish.common.template.BizHandleTemplate;
import com.balfish.common.template.BizProcessCallBackNoResult;
import com.balfish.hotel.enums.HotelGradeType;
import com.balfish.hotel.model.HotelEntity;
import com.balfish.hotel.service.HotelService;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

@Service
public class HotelBiz {

    @Resource
    private HotelService hotelService;

    public List<HotelEntity> query(Integer id) {
        return hotelService.query(id);
    }

    public void add(final HotelEntity hotelEntity) {
        BizHandleTemplate.executeNoResult(new BizProcessCallBackNoResult() {
            @Override
            public void checkParams() {
                Preconditions.checkNotNull(hotelEntity, "hotelEntity不能为空");
            }

            @Override
            public void process() {
                hotelService.add(hotelEntity);
            }

            @Override
            public void succMonitor(long execTime) {
                // 监控
            }

            @Override
            public void failMonitor() {
                // 监控 & 日志
            }
        });
    }

    @Transactional
    public void addTx() throws Exception {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setHotelId(2);
        hotelEntity.setHotelName("1");
        hotelEntity.setHotelAddress("1");
        hotelEntity.setHotelPhone("1");
        hotelEntity.setHotelGradeType(HotelGradeType.STAR_ONE);
        hotelEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        hotelEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.add(hotelEntity);

        throw new Exception("ss");
    }
}
