package com.test.future;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/4/23
 * @Author yangfa
 * @Description
 */
public class BumThread extends Thread{
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
