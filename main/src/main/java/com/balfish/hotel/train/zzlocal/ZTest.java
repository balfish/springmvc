package com.balfish.hotel.train.zzlocal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by yhm on 2017/9/25 PM4:06.
 */
public class ZTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        String shpath = "/Users/balfish/date.sh";
        Process ps = Runtime.getRuntime().exec(shpath);
        ps.waitFor();


        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        String result = sb.toString();
        System.out.println(result);


        Thread thread = new Thread(() -> System.out.println("dsds"));
        thread.start();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i<18; i++) {
            map.put(i, i);
        }
        System.out.println(map);

        float ft = ((float)(18) / 0.75F) + 1.0F;


        System.out.println(ft);

    }

}
