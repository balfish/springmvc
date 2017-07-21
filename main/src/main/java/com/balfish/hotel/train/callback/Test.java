package com.balfish.hotel.train.callback;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class Test {

    public static void main(String[] args) {
        Asker asker = new Asker();
        asker.setAnswerer(new Answerer());
        asker.askQuestion();
    }
}
