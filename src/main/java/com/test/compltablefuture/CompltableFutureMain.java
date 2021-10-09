package com.test.compltablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Date 2021/10/8
 * @Author yangfa
 * @Description
 */
public class CompltableFutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<Shop> shopList = Arrays.asList(
                new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

        long startTime = System.currentTimeMillis();
//        findPriceSync(shopList);
//        findPriceAsync(shopList);
        findPriceFutureAsync1(shopList);
        long endTime = System.currentTimeMillis();
        System.out.println("用时：" + (endTime - startTime) + "毫秒");
    }

    private static List<String> findPriceSync(List<Shop> shopList) {
        List<String> collect = shopList.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice()))  //格式转换
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }

    private static List<String> findPriceAsync(List<Shop> shopList) {
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
                //转异步执行
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(), shop.getPrice())))  //格式转换
                .collect(Collectors.toList());

        List<String> collect = completableFutureList.stream()
                .map(CompletableFuture::join)  //获取结果不会抛出异常
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }

    private static List<String> findPriceFutureAsync(List<Shop> shopList) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> futureList = shopList.stream().map(shop -> es.submit(() -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice()))).collect(Collectors.toList());

        List<String> collect = futureList.stream()
                .map(f -> {
                    String result = null;
                    try {
                        result = f.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    return result;
                }).collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }

    private static List<String> findPriceFutureAsync1(List<Shop> shopList) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> futureList = shopList.stream().map(shop -> es.submit(() -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice()))).collect(Collectors.toList());

        List<String> collect = futureList.stream().map(future -> {
            String result = null;
            try {
                result = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return result;
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }
}