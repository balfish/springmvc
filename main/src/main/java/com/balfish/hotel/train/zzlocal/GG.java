package com.balfish.hotel.train.zzlocal;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by yhm on 2017/8/7 PM4:11.
 */
public class GG implements Serializable {
    private static final long serialVersionUID = -4721021661124755443L;

    public static void main(String[] args) {
        //B00000100001-B00000100100
        int i = 1;
        StringBuilder sb = new StringBuilder();
        String baseStr = "B00000100";
        while (true) {
            if (i > 100) {
                break;
            }

            if (i % 10 == 1 && i != 1) {
                sb.append("\n");
            }
            String tmpStr = baseStr + placeHolder(i++) + ",";
            sb.append(tmpStr);
        }
        String substring = sb.substring(0, sb.length() - 1);//去掉后导逗号
        System.out.println(substring);
    }

    private static String placeHolder(int i) {
        String str = StringUtils.EMPTY;
        if (i < 10) {
            str = "00" + i;
        } else if (i < 100 && i >= 10) {
            str = "0" + i;
        } else {
            str += i;
        }
        return str;
    }
}
