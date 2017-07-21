package com.balfish.hotel.train.callback;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class Answerer {

    public void answer(CallBack callBack) {
        System.out.println("answer has thought of a lot");
        System.out.println(callBack.execute());
    }
}
