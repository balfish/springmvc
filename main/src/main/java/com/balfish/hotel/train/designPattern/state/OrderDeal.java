package com.balfish.hotel.train.designPattern.state;

import com.balfish.hotel.train.designPattern.state.orderStateEnum.InitOrderState;
import com.balfish.hotel.train.designPattern.state.orderStateEnum.OrderState;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yhm on 2017/8/22 AM10:23.
 */
public class OrderDeal {

    private long orderNum;

    private OrderState orderState;

    public OrderDeal() {
        orderState = new InitOrderState();
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        handle();
    }

    private void handle() {
        orderState.handle(this);
    }

    public static void main(String[] args) throws Exception {
        OrderDeal orderDeal = new OrderDeal();
        orderDeal.setOrderNum(1L);
        orderDeal.setOrderState(new InitOrderState());

        startShutdownHook();
    }


    private static void startShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
//                    close();
                System.out.println("钩子");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }


}
