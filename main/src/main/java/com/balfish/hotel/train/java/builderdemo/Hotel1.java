package com.balfish.hotel.train.java.builderdemo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p>
 * builder设计模式
 */
public class Hotel1 {

    private String tel;
    private int price;

    public static <E> Builder<E> builder() {
        return new Builder<E>();
    }

    public static final class Builder<E> {
        Hotel1 hotel = new Hotel1();

        public Builder<E> setPrice(int price) {
            hotel.setPrice(price);
            return this;
        }

        public Builder<E> setTel(String tel) {
            hotel.setTel(tel);
            return this;
        }

        public Hotel1 build() {
            return hotel;
        }
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static void main(String[] args) {
        Hotel1 hotel = Hotel1.builder().setPrice(144).setTel("111111111").build();
        System.out.println(hotel);
    }
}
