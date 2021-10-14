package com.test.compltablefuture;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Data
public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    /**
     * 根据产品名查找价格
     */
    public double getPrice() {
        return calculatePrice(name);
    }

    /**
     * 计算价格
     *
     * @param product
     * @return
     */
    private double calculatePrice(String product) {
        sleep();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 通过睡眠模拟其他耗时操作,IO 或者是 RPC调用
     */
    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}