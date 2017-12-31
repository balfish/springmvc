package com.balfish.hotel.train.proxy;

/**
 * Created by yhm on 2017/12/12 AM11:44.
 */
public class ProxyMain {

    public static void main(String[] args) {
        ProxyHandler proxyHandler = new ProxyHandler();
        // qmq
        SubjectProvider qmqSubjectProvider = (SubjectProvider) proxyHandler.bind(new QmqSubjectProvider());
        System.out.println(qmqSubjectProvider.getSubject("1"));
        // qconfig
        SubjectProvider qconfigSubjectProvider = (SubjectProvider) proxyHandler.bind(new QconfigSubjectProvider());
        System.out.println(qconfigSubjectProvider.getSubject("1"));
    }
}
