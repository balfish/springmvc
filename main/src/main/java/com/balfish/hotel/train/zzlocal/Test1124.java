package com.balfish.hotel.train.zzlocal;

import com.balfish.common.ApiResult;
import com.balfish.common.utils.json.JsonUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by yhm on 2017/11/24 AM11:08.
 */
public class Test1124 {

    public static void main(String[] args) {

        String cmd = "head -n 80 /dev/urandom | tr -dc A-Za-z0-9 | head -c 168";
        String[] cmdA = {"/bin/sh", "-c", cmd};

        try {
            Process process = Runtime.getRuntime().exec(cmdA);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            System.out.println(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ImmutableTriple<Integer, Integer, Integer> immutableTriple = ImmutableTriple.of(1, 2, 3);
        System.out.println(immutableTriple.getLeft());
        System.out.println(immutableTriple.getMiddle());
        System.out.println(immutableTriple.getRight());



        String json = JsonUtils.toJson(immutableTriple);
        System.out.println(json);
//        Triple triple1 = JsonUtils.toBean(json, ImmutableTriple.class, Integer.class, Integer.class, Integer.class);

//
//        System.out.println("------------------------");
//        System.out.println(triple1);


        double a = 23.36;

        String b = String.valueOf(a);

        String d = b.substring(0, b.lastIndexOf('.'));
        System.out.println(d);

        System.out.println("9s8GirVlJMoY5r9eG808FcZp3CjxopPsbIFjIHkCj6XX5Ds0aoH5lbGNfieC7bcbnUDGZoVk2LeOrabX1gVA56b0gOCiuPp47CUUOHGuGMJZe8nOJO8q36jIYq0z9E5u6AtOUlUVJeefjmcWp9Vi1ziRLZjUHECu63mRzJnQ".length());


        String s = JsonUtils.toJson("9s8GirVlJMoY5r9eG808FcZp3CjxopPsbIFjIHkCj6XX5Ds0aoH5lbGNfieC7bcbnUDGZoVk2LeOrabX1gVA56b0gOCiuPp47CUUOHGuGMJZe8nOJO8q36jIYq0z9E5u6AtOUlUVJeefjmcWp9Vi1ziRLZjUHECu63mRzJnQ");
        System.out.println(JsonUtils.toJson(ApiResult.buildSuccessResult(s)));

        String markUrl = "http://mta.meituan.com/supplier/pages/embed/embed.html?partnerId=931871";
        String judgeUri = markUrl.substring(0, markUrl.lastIndexOf('?'));
        String judgeUri1 = markUrl.substring(markUrl.indexOf("//") + 2, markUrl.length() - 1);
        String judgeUri2 = judgeUri1.substring(0, judgeUri1.indexOf('/'));
        System.out.println(judgeUri1);
        System.out.println(judgeUri2);

        List<String> list = Lists.newArrayList();

        System.out.println(getDispMobile("18514594686"));


    }


    private static String getDispMobile(String mobile) {
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}
