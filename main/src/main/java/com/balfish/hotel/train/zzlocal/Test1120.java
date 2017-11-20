package com.balfish.hotel.train.zzlocal;

import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * Created by yhm on 2017/11/20 PM3:37.
 */
public class Test1120 {

    public static void main(String[] args) throws IOException {
        System.out.println("link start");

        CloseableHttpResponse response = HttpClients.createDefault().execute(new HttpGet("http://www.baidu.com"));

        InputStream inputStream = response.getEntity().getContent();
        InputStream copyInputStream = new Base64InputStream(inputStream);


        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String str;
        while((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

        System.out.println("------------------------");

        byte[] byteArray = new byte[1024];
        int len;
        while((len = response.getEntity().getContent().read(byteArray)) != -1) {
            System.out.write(byteArray, 0, len);
        }


    }


}
