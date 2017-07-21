package com.balfish.hotel.train.java.javademo;

import com.balfish.hotel.bean.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class JavaDemo {
    public static void main(String[] args) throws Exception {

        // 字符编码问题　　
        encodeDemo();

        // transient问题
//        transientDemo();
    }

    /**
     * <pre>
     *  java语言的关键字，变量修饰符，如果用transient声明一个实例变量，
     *  当对象存储时，它的值不需要维持。换句话来说就是，用transient关键字标记的成员变量不参与序列化过程。
     * </pre>
     */
    private static void transientDemo() throws Exception {
        User user = new User();
        user.setName("Alexia");
        user.setAge(18);

        System.out.println("read before Serializable: ");
        System.out.println("name: " + user.getName());
        System.err.println("age: " + user.getAge());

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/home/balfish/test.txt"));
        os.writeObject(user); // 将User对象写进文件
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("/home/balfish/test.txt"));
        user = (User) is.readObject(); // 从流中读取User的数据

        System.out.println("\nread after Serializable: ");
        System.out.println("name: " + user.getName());
        System.err.println("age: " + user.getAge());

        os.flush();
        os.close();
        is.close();
    }

    private static void encodeDemo() {
        String s = "你们好";
        Charset gbk = Charset.forName("gbk");
        Charset utf8 = Charset.forName("utf-8");
        byte[] b1 = s.getBytes(gbk);
        String str = new String(b1, utf8);
        String str1 = new String(b1, gbk);
        System.out.println(str);
        System.out.println(str1);
    }
}
