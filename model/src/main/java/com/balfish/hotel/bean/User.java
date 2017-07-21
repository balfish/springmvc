package com.balfish.hotel.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p/>
 * 这个bean公共来玩的, come on～
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3251420487392064380L;

    public User() {
    }

    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    private String name;

    private transient int age;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    /**
     * jackson序列化不拼接null字段，比如
     * User user = new User("heh", 0, null);
     * 结果:{"name":"heh","age":0}
     * <p/>
     * 去掉@JsonSerialize这个注解后,
     * 结果:{"name":"heh","age":0,"address":null}
     */
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
