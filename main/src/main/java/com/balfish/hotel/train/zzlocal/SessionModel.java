package com.balfish.hotel.train.zzlocal;

import com.balfish.common.utils.json.JsonUtils;

import java.io.Serializable;

/**
 * Created by yhm on 2017/12/4 AM11:40.
 */


public class SessionModel implements Serializable {

    private static final long serialVersionUID = -7619521697045841417L;
    private String phoneNumber;

    private String purePhoneNumber;

    private String countryCode;

//    private Watermark watermark;

//    class Watermark implements Serializable {
//        private static final long serialVersionUID = -5341027316984907772L;
//        private String appId;
//        private String timestamp;
//
//        public String getAppId() {
//            return appId;
//        }
//
//        public void setAppId(String appId) {
//            this.appId = appId;
//        }
//
//        public String getTimestamp() {
//            return timestamp;
//        }
//
//        public void setTimestamp(String timestamp) {
//            this.timestamp = timestamp;
//        }
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurePhoneNumber() {
        return purePhoneNumber;
    }

    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public static void main(String[] args) {

        String json = "{\n" +
                "    \"phoneNumber\": \"13580006666\",  \n" +
                "    \"purePhoneNumber\": \"13580006666\", \n" +
                "    \"countryCode\": \"86\",\n" +
                "    \"watermark\":\n" +
                "    {\n" +
                "        \"appid\":\"APPID\",\n" +
                "        \"timestamp\":\"sdasd\"\n" +
                "    }\n" +
                "}";


        String json2 = "{\n" +
                "    \"phoneNumber\": \"13580006666\",\n" +
                "    \"purePhoneNumber\": \"13580006666\",\n" +
                "    \"countryCode\": \"86\"\n" +
                "}";
        System.out.println(json);
        System.out.println(JsonUtils.toBean(json, SessionModel.class));
    }

    @Override
    public String toString() {
        return "SessionModel{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", purePhoneNumber='" + purePhoneNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
