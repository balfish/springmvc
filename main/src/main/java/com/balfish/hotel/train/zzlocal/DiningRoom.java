package com.balfish.hotel.train.zzlocal;

import com.balfish.common.ApiResult;
import com.balfish.common.utils.json.JsonUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class DiningRoom {

    private String desc;
    private CoverEnum coverEnum;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CoverEnum getCoverEnum() {
        return coverEnum;
    }

    public void setCoverEnum(CoverEnum coverEnum) {
        this.coverEnum = coverEnum;
    }

    public static void main(String[] args) {


        List<DiningRoom> diningRoomList = Lists.newArrayList();

        DiningRoom diningRoom = new DiningRoom();
        diningRoom.setCoverEnum(CoverEnum.COVER);
        diningRoom.setDesc("客房WIFI覆盖");
        diningRoomList.add(diningRoom);

        DiningRoom diningRoom1 = new DiningRoom();
        diningRoom1.setCoverEnum(CoverEnum.PARTIAL_COVER);
        diningRoom1.setDesc("24小时热水");
        diningRoomList.add(diningRoom1);

        String s = JsonUtils.toJson(diningRoomList);
        System.out.println(s);

//        List<Map> diningRoomList = Lists.newArrayList();
//        Map<String, Integer> map = Maps.newHashMap();
//        map.put("客房WIFI覆盖", CoverEnum.COVER.getCode());
//        diningRoomList.add(map);
//
//        Map<String, Integer> map1 = Maps.newHashMap();
//        map1.put("24小时热水", CoverEnum.PARTIAL_COVER.getCode());
//        diningRoomList.add(map1);
//
//        Object[] objects =  diningRoomList.toArray();
//        System.out.println(objects);
//        String s = JsonUtils.toJson(diningRoomList.toArray());
//        System.out.println(s);

        List<String> list = Lists.newArrayList();
        list.add("安逸158连锁酒店");
        list.add("格林豪泰酒店");
        System.out.println(JsonUtils.toJson(list));


        System.out.println(JsonUtils.toJson(ApiResult.buildSuccessResult()));
    }
}
