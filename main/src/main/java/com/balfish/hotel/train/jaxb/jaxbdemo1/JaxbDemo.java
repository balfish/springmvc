package com.balfish.hotel.train.jaxb.jaxbdemo1;

import com.google.common.collect.Sets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class JaxbDemo {

    /**
     * <pre>
     * 概念是什么：（Java Architecture for XML Binding) 是一个业界的标准，即是一项可以进行XML Schema和Java类互转的技术
     * 依赖JDK5以下开发需要的jar包：activation.jar、jaxb-api.jar、 jaxb-impl.jar、 jsr173-api.jar;
     * 如果是基于JDK6以上版本已经集成JAXB2的JAR，在目录{JDK_HOME}/jre/lib/rt.jar中。
     *
     * 编组（java对象转xml），提供多种编组目的地，以下只介绍两种，一种编组到文件，一种编组到控制台
     *
     * </pre>
     * <p>
     * <p>
     * <pre>
     *     <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     *      <customer xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" id="1" xsi:schemaLocation="s" xsi:noNamespaceSchemaLocation="w">
     *      <age>18</age>
     *      <bookHashSet>
     *      <name>小人书</name>
     *      <price>22</price>
     *      </bookHashSet>
     *      <bookHashSet>
     *      <name>编程书</name>
     *      <price>62</price>
     *      </bookHashSet>
     *      <name>sam</name>
     *      </customer>
     * </pre>
     */

    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        String path = "/home/balfish/jaxb.txt";
        marshallerByJaxb(path, new Customer(), Customer.class);

        Customer customer = (Customer) unmarshallerByJaxb(path, Customer.class);
        System.out.println("xml转化为bean后的结果  " + customer.toString());
    }

    public static void marshallerByJaxb(String path, Object obj, Class clazz) {
        HashSet<Book> bookSet = Sets.newHashSet();
        bookSet.add(new Book("小人书", 22));
        bookSet.add(new Book("编程书", 62));

        Customer customer = (Customer) obj;
        customer.setId(1);
        customer.setName("sam");
        customer.setAge(18);
        customer.setBookHashSet(bookSet);

        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // 用来指定将放置在已编组 XML 输出中的 xsi:schemaLocation 属性值的属性名称
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "s");

            // 用来指定将放置在已编组 XML 输出中的 xsi:noNamespaceSchemaLocation 属性值的属性名称
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "w");

            marshaller.marshal(customer, file);
            marshaller.marshal(customer, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Object unmarshallerByJaxb(String path, Class clazz) throws JAXBException, FileNotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("can not load xml file!");
        }
        return unmarshaller.unmarshal(file);
    }

}

