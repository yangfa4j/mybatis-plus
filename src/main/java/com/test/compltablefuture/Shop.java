package com.test.compltablefuture;

import lombok.Data;

import java.util.Random;

@Data
public class Shop {

    private String name;

    private Random random = new Random();

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
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 通过睡眠模拟其他耗时操作
     */
    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Shop(String name) {
        this.name = name;
    }
}