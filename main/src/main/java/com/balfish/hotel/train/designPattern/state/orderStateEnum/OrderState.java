package com.balfish.hotel.train.designPattern.state.orderStateEnum;

import com.balfish.hotel.train.designPattern.state.OrderDeal;

/**
 * Created by yhm on 2017/8/22 AM10:23.
 */
public abstract class OrderState {

    public abstract void handle(OrderDeal orderDeal);
}
