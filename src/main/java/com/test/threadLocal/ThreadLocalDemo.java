package com.test.threadLocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo.ThreadLocalTest();
    }


    public static void ThreadLocalTest() {
        //新建一个ThreadLocal
        ThreadLocal<String> local = new ThreadLocal<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    local.set(String.valueOf(finalI));
                    System.out.println("线程和local值分别是  " + local.get());
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}