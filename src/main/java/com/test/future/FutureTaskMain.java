package com.test.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Date 2021/9/30
 * @Author yangfa
 * @Description
 */
public class FutureTaskMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 开始时间
        long starttime = System.currentTimeMillis();
        //input1生成， 需要耗费3秒
        FutureTask<Integer> futureTask1 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 5;
            }
        });
        //input2生成，需要耗费2秒
        FutureTask<Integer> futureTask2 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 3;
            }
        });
        // 开启两个线程
        new Thread(futureTask1).start();
        new Thread(futureTask2).start();
        // 获取 FutureTask 结果
        Integer integer2 = futureTask1.get();
        Integer integer1 = futureTask2.get();
        System.out.println(integer1 + integer2);
        // 结束时间
        long endtime = System.currentTimeMillis();
        System.out.println("用时：" + (endtime - starttime) + "毫秒");
    }
}
