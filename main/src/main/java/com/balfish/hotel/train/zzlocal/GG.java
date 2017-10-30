package com.balfish.hotel.train.zzlocal;

import com.balfish.common.utils.json.JsonUtils;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yhm on 2017/8/7 PM4:11.
 */
public class GG implements Serializable {
    private static final long serialVersionUID = -4721021661124755443L;

    public static void main(String[] args) throws InterruptedException {
        // 测试代码

        Integer id = 1;
        String s = JsonUtils.toJson(id);
        System.out.println(s);

        Bean bean = new Bean();
        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1,1);
        bean.setId(12);
        bean.setMap(map);
        System.out.println(JsonUtils.toJson(bean));


        System.out.println(JsonUtils.toJson("abc"));

    }
}

class Bean {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private Map<Integer, Integer> map;

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }
}
