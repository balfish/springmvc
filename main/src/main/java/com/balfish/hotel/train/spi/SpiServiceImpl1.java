package com.balfish.hotel.train.spi;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class SpiServiceImpl1 implements SpiService {

    @Override
    public void test() {
        System.out.println("child spi service test");
    }
}