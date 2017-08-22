package com.balfish.hotel.train.designPattern.state.orderStateEnum;

import com.balfish.hotel.train.designPattern.state.OrderDeal;

/**
 * Created by yhm on 2017/8/22 AM10:25.
 */
public class InitOrderState extends OrderState {
    @Override
    public void handle(OrderDeal orderDeal) {
        System.out.println("order init");
        // 初始化订单操作

        if (orderDeal.getOrderNum() > 2L) { //例子未必恰当，仅为了说明问题
            orderDeal.setOrderState(new PaySuccOrderState());
        } else {
            orderDeal.setOrderState(new PayFailOrderState());
        }
    }
}
