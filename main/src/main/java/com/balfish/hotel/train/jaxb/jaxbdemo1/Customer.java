package com.balfish.hotel.train.jaxb.jaxbdemo1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
@XmlRootElement
public class Customer {

    /**
     * 创建要转化的java对象，该对象需要使用相关注解 标注在get方法上
     */

    private int id;
    private String name;
    private int age;
    private HashSet<Book> bookHashSet;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement
    public HashSet<Book> getBookHashSet() {
        return bookHashSet;
    }

    public void setBookHashSet(HashSet<Book> bookHashSet) {
        this.bookHashSet = bookHashSet;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", age='" + age + '\'' + '}';
    }
}
