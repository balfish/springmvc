package com.balfish.hotel.train.jaxb.jaxbdemo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class JaxbReadXml {

    private static final Logger LOGGER = LoggerFactory.getLogger(JaxbReadXml.class);

    @SuppressWarnings("unchecked")
    /** 不加这个下面的  return (T) unmarshaller.unmarshal(new File(path));　会报警告　*/
    public static <T> T readXMLtoObject(Class<T> clazz, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new File(path));
        } catch (Exception e) {
            LOGGER.error("解析出错", e);
        }
        return null;
    }

    public static String writeObjectToXML(Object object, String path) {
        try {
            Class clazz = object.getClass();
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(object, new File(path));
        } catch (Exception e) {
            LOGGER.error("解析出错", e);
        }
        return null;
    }

    public static void main(String[] args) throws JAXBException {
        String path = JaxbReadXml.class.getResource("/test.xml").getPath();
        TestOrgs testOrgs = JaxbReadXml.readXMLtoObject(TestOrgs.class, path);
        System.out.println(testOrgs);
        for (TestOrg o1 : testOrgs) {
            System.out.println(o1);
        }
        /**
         * 如果output文件存在,下面两个方案均可，若不是的话，方案一会报npe, 方案二没问题，故建议JaxbReadXml.class.getResource("/").getPath() + "output.txt" 的写法
         * 下面这个会报错　Exception in thread "main" java.lang.NullPointerException
         */
        writeObjectToXML(testOrgs, JaxbReadXml.class.getResource("/output.txt").getPath());
//        writeObjectToXML(testOrgs, JaxbReadXml.class.getResource("/").getPath() + "output.txt");
    }
}
