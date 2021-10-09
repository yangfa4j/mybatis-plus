package com.test.future;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/4/23
 * @Author yangfa
 * @Description 准备凉菜的线程，需要花费1秒钟
 */
public class ColdDishThread extends Thread {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("凉菜准备完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
