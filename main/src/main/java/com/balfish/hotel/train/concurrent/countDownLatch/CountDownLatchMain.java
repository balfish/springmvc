package com.balfish.hotel.train.concurrent.countDownLatch;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by yhm on 2017/11/9 AM10:55.
 */
public class CountDownLatchMain {

    private static final int LATCH_NUMBER = 3;

    public static void main(String[] args) throws InterruptedException {

        List<BasePreparer> preparerList = Lists.newArrayListWithExpectedSize(LATCH_NUMBER);
        CountDownLatch countDownLatch = new CountDownLatch(LATCH_NUMBER);

        preparerList.add(new ResourcePreparer(countDownLatch));
        preparerList.add(new DataPreparer(countDownLatch));
        preparerList.add(new NetworkPreparer(countDownLatch));

        ExecutorService executorService = Executors.newFixedThreadPool(LATCH_NUMBER);
        preparerList.forEach(executorService::execute);
        countDownLatch.await();

        List<BasePreparer> readyList = preparerList.stream().filter(BasePreparer::isPrepareDone).collect(Collectors.toList());
        System.out.println(readyList.size() == LATCH_NUMBER);

    }
}
