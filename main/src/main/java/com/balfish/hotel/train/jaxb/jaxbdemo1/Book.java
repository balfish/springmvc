package com.balfish.hotel.train.jaxb.jaxbdemo1;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class Book {
    private String name;
    private int price;

    public Book() {
    }

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}

