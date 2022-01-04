package com.test.compltablefuture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Date 2021/10/8
 * @Author yangfa
 * @Description
 */
public class CompltableFutureMain {

    public static void main(String[] args) throws Exception {

      testScheduledExecutorService();
    }

    /**
     * 同步调用获取商品价格
     */
    private static List<String> findPriceSync(List<Shop> shopList) {
        List<String> collect = shopList.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice()))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        return collect;
    }

    /**
     * 异步方式，使用 Future + ExecutorService
     */
    private static List<String> findPriceWithFuture(List<Shop> shopList) {
        // 线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 提交到线程池
        List<Future<String>> futureList = shopList.stream()
                .map(shop -> es.submit(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice())))
                .collect(Collectors.toList());

        List<String> strings = futureList.stream()
                .map(future -> {
                    String result = null;
                    try {
                        result = future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return result;
                }).collect(Collectors.toList());
        strings.forEach(System.out::println);
        return strings;
    }

    /**
     * 异步方式，FutureTask + ExecutorService
     */
    private static List<String> findPriceWithFutureTask(List<Shop> shopList){
        // 线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 包装成 FutureTask 并提交到线程池
        List<FutureTask<String>> futureTasks = shopList.stream()
                .map(shop -> {
                    FutureTask<String> stringFutureTask = new FutureTask<>(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice()));
                    es.submit(stringFutureTask);
                    return stringFutureTask;
                }).collect(Collectors.toList());

        List<String> strings = futureTasks.stream()
                .map(future -> {
                    String result = null;
                    try {
                        result = future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return result;
                }).collect(Collectors.toList());
        strings.forEach(System.out::println);
        return strings;
    }

    /**
     * 异步方式，使用 CompletableFuture
     */
    private static List<String> findPriceWithCompletableFuture(List<Shop> shopList) {
        // 获取产品价格，都用异步来实现
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
                //转 CompletableFuture 执行
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice())))
                .collect(Collectors.toList());

        List<String> strings = completableFutureList.stream()
                .map(CompletableFuture::join)  //获取结果不会抛出异常
                .collect(Collectors.toList());
        strings.forEach(System.out::println);
        return strings;
    }

    /**
     * 异步方式，使用 CompletableFuture + CountDownLatch
     */
    private static List<String> findPriceWithCompletableFutureCountDownLatch(List<Shop> shopList) throws Exception {
        // CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(shopList.size());
        // 获取产品价格，都用异步来实现。
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
                //转 CompletableFuture 执行 且执行完后 countDownLatch -1
                .map(shop -> CompletableFuture.supplyAsync(() -> {
                    countDownLatch.countDown();
                    return String.format("%s price is %.2f", shop.getName(), shop.getPrice());
                })).collect(Collectors.toList());
        // 获取 CompletableFutures 的结果
        List<String> strings = completableFutureList.stream()
                .map(CompletableFuture::join)  //获取结果不会抛出异常
                .collect(Collectors.toList());
        // 等到所有异步操作执行完
        countDownLatch.await();
        strings.forEach(System.out::println);
        return strings;
    }



    private static void testCompltableFuture() throws Exception {
        //getNow方法测试
        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            try {
                // 睡5s
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        });
        // 获取不到 返回 hello
        System.out.println(cp1.getNow("hello"));

        //get方法测试，抛出 java.lang.ArithmeticException: / by zero
        CompletableFuture<Integer> cp3 = CompletableFuture.supplyAsync((() -> 1 / 0));
        System.out.println(cp3.get());

        //join方法测试
        CompletableFuture<Integer> cp2 = CompletableFuture.supplyAsync((() -> 1 / 0));
        System.out.println(cp2.join());

    }

    private static void testScheduledExecutorService() throws Exception {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 创建并执行在给定延迟后启用的一次性操作
        scheduledExecutorService.schedule(() -> System.out.println("一次性执行，当前时间 = " + LocalDateTime.now()),5,TimeUnit.SECONDS);

        // 创建并执行一个周期性动作，在给定的初始延迟后首先启用，然后在给定的时间段内启用
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("延迟一秒后，间隔三秒执行一次，当前时间 = " + LocalDateTime.now()),1,3,TimeUnit.SECONDS);

        // 创建并执行一个周期性动作，在给定的初始延迟后首先启用，然后在给定的时间段内启用
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("延迟一秒后，间隔三秒执行一次，当前时间 = " + LocalDateTime.now()),1,3,TimeUnit.SECONDS);
    }
}