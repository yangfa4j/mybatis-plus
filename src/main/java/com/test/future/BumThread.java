package com.test.future;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/4/23
 * @Author yangfa
 * @Description 准备包子的线程，需要花费3秒钟
 */
public class BumThread extends Thread {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("包子准备完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
