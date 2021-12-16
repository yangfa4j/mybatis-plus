package com.test.future;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        // 开始时间
        long starttime = System.currentTimeMillis();
        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //input1生成， 需要耗费3秒
        Future<Integer> future1 = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 5;
        });
        //input2生成， 需要耗费2秒
        Future<Integer> future2 = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 1;
        });
        // 获取 Future 结果
        Integer integer1 = future1.get();
        Integer integer2 = future2.get();
        // 结束时间
        long endtime = System.currentTimeMillis();
        System.out.println(integer1 + integer2);
        System.out.println("用时：" + (endtime - starttime) + "毫秒");
    }
}