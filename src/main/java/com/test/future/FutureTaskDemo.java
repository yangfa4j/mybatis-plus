package com.test.future;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 开始时间
        long starttime = System.currentTimeMillis();
        // 线程池
        ExecutorService es = Executors.newCachedThreadPool();
        //FutureTask1, 生成 integer1 ， 需要耗费3秒
        FutureTask<Integer> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 5;
        });
        //FutureTask2, 生成 integer2 ， 需要耗费2秒
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 1;
        });
        // 提交到线程池,多线程运行
        es.submit(futureTask1);
        es.submit(futureTask2);
        // 获取 Future 结果
        Integer integer1 = futureTask1.get();
        Integer integer2 = futureTask2.get();
        // 结束时间
        long endtime = System.currentTimeMillis();
        System.out.println(integer1 + integer2);
        System.out.println("用时：" + (endtime - starttime) + "毫秒");
    }
}