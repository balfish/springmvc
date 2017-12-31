package com.balfish.hotel.train.zzlocal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashSet;

/**
 * Created by xxx on 2017/12/15 PM3:29.
 */
public class Test1215 {

    public static void main(String[] args) {
        AUser aUser = new AUser(18, "zhangsan", "beijing");
        AUser aUser1 = new AUser(19, "lisi", "shanghai");
        AUser aUser2 = new AUser(18, "zhangsan", "beijing");

        HashSet<AUser> hashSet = new HashSet<>();
        hashSet.add(aUser);
        hashSet.add(aUser1);
        hashSet.add(aUser2);

        System.out.println(hashSet);
    }

}

class AUser {
    private int age;
    private String name;
    private String address;

    public AUser(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

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
        if (!(o instanceof AUser)) return false;

        AUser user = (AUser) o;

        return age == user.getAge() && name.equals(user.getName());
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}
