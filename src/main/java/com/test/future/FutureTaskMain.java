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

        long starttime = System.currentTimeMillis();

        //input2生成， 需要耗费3秒
        FutureTask<Integer> input2_futuretask = new FutureTask<>(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 5;
            }
        });

        //input1生成，需要耗费2秒
        FutureTask<Integer> input1_futuretask = new FutureTask<>(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 3;
            }
        });

        new Thread(input2_futuretask).start();
        new Thread(input1_futuretask).start();

        Integer integer2 = input2_futuretask.get();
        Integer integer1 = input1_futuretask.get();

        System.out.println(add(integer1, integer2));
        long endtime = System.currentTimeMillis();
        System.out.println("用时：" + (endtime - starttime) + "毫秒");
    }

    //这是我们要执行的算法
    public static int add(int input, int input2) {
        return input + input2;
    }
}
