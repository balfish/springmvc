package com.balfish.hotel.train.guava;

import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class XxxTest {

    //  "%s\\%\\(%s\\)";
    private static final String DISPLAY_TEXT = "%s%%(%s)";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * junit单元测试
     */
    @Test
    public void testAssertDemo() {
        //output:2016-10-19 15:18:58
        DateTime dateTime = new DateTime(1390358185777L);
        System.out.println(dateTime.minusMinutes(30).toString("yyyy-MM-dd HH:mm:ss"));

        //output:1476863338000L
        Timestamp timestamp = Timestamp.valueOf("2016-10-19 15:48:58");
        System.out.println(timestamp.getTime());

        System.out.println(String.format("I am a  %s, %s", null, "暂未货渠道"));


        System.out.println(new DateTime(1479312000000L).toString("yyyy-MM-dd HH:mm:ss"));

        List<String> serialList = Arrays.asList("SN12345", "SN12345", "SN56789");
        Set<String> unvisit = Sets.newHashSet(serialList);
        System.out.println(unvisit);

        BigDecimal decimal = new BigDecimal("1.12345");
        System.out.println(decimal);
        BigDecimal setScale = decimal.setScale(4, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(setScale);

        BigDecimal setScale1 = decimal.setScale(4, BigDecimal.ROUND_HALF_UP);
        System.out.println(setScale1.toString());

        System.out.println(Pattern.compile(DISPLAY_TEXT).pattern());
        System.out.println(String.format(DISPLAY_TEXT, "1.2", "50"));
    }
}
