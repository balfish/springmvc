package com.balfish.hotel.train.proxy;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class QconfigSubjectProvider implements SubjectProvider {
    @Override
    public String getSubject(String prefix) {
        return prefix + " qconfigSubject";
    }
}
