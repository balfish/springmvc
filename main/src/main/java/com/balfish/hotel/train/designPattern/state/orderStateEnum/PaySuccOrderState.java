package com.balfish.hotel.train.designPattern.state.orderStateEnum;

import com.balfish.hotel.train.designPattern.state.OrderDeal;

/**
 * Created by yhm on 2017/8/22 AM10:26.
 */
public class PaySuccOrderState extends OrderState {
    @Override
    public void handle(OrderDeal orderDeal) {
        System.out.println("pay succ");
    }
}
