package com.balfish.hotel.train.java.clone;

import com.balfish.common.utils.clone.CloneUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
class Professor implements Serializable, Cloneable {
    private static final long serialVersionUID = 1286716519490813020L;
    public String name;
    public int age;

    // 构造函数是为了CloneUtils json序列化使用
    public Professor() {
    }

    public Professor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class Student implements Serializable, Cloneable {
    private static final long serialVersionUID = -547004870369127943L;
    public String name;
    public int age;
    public Professor p;

    // 构造函数是为了CloneUtils工具类clazz.instance()方法调用 &  json序列化使用
    public Student() {
    }

    public Student(String name, int age, Professor p) {
        this.name = name;
        this.age = age;
        this.p = p;
    }

    public Object shadowClone() throws CloneNotSupportedException {
        Student s = (Student) super.clone();
        //s.p = (Professor) p.clone();  //这句加上就是深拷贝了
        return s;
    }

    // 序列化形式的深拷贝
    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        return objectInputStream.readObject();
    }
}

class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException {
        Professor p = new Professor("教授1", 50);
        Student s1 = new Student("学生1", 18, p);
//        Student s2 = (Student) s1.shadowClone();
//        Student s2 = CloneUtils.shadowClone(s1);
        Student s2 = CloneUtils.deepClone(s1, Student.class);
        s2.p.name = "教授2";
        s2.p.age = 30;
        s2.name = "学生2";
        s2.age = 25;
        System.out.println(s1.name + " " + s1.age + " " + s1.p.name + " " + s1.p.age);
    }
}


