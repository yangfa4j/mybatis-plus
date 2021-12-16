package com.test.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Date 2021/4/21
 * @Author yangfa
 * @Description
 */
@Slf4j
public class ThteadTest {
    public static void main(String[] args) throws Exception {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        // 可以返回结果
//        Callable<String> callable = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                log.info(" 进入callable 的 call 方法 ");
//                TimeUnit.SECONDS.sleep(5);
//                return "callable 执行完毕";
//            }
//        };
//
//        log.info("提交 callable 到线程池");
//        Future<String> submit = executorService.submit(callable);
//
//        log.info("主线程继续执行");
//
//        log.info("主线程获取执行结果");
//        while (!submit.isDone()) {
//            log.info("task not done");
//            TimeUnit.SECONDS.sleep(1);
//        }
//        String s = submit.get();
//        log.info("callable执行结果是：{}", s);
//        executorService.shutdown();


//        threadTest();
//        futureTaskThreadTest();
//        futureTaskExecutorServiceTest();
        futureTaskThreadTest();
    }

    /**
     * 继承 thread 类
     */
    public static void threadTest() throws Exception {
        // 开始时间
        long start = System.currentTimeMillis();
        // 准备凉菜，花费1秒钟 -- 必须要等待返回的结果，所以要调用join方法
        ColdDishThread coldDishThread = new ColdDishThread();
        coldDishThread.start();
        coldDishThread.join();
        // 准备包子，花费3秒钟 -- 必须要等待返回的结果，所以要调用join方法
        BumThread bumThread = new BumThread();
        bumThread.start();
        bumThread.join();
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间：" + (end - start));
    }

    /**
     * future + ExecutorService 使用
     */
    public static void futureExecutorServiceTest() throws Exception {
        // 开始时间
        long start = System.currentTimeMillis();
        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // callable -- 准备凉菜，花费1秒钟
        Callable callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "凉菜准备完成";
            }
        };
        // callable -- 准备包子，花费3秒钟
        Callable callable2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "包子准备完成";
            }
        };
        // 提交到线程池
        Future future1 = executorService.submit(callable1);
        Future future2 = executorService.submit(callable2);
        // 获取 future 结果
        System.out.println(future1.get());
        System.out.println(future2.get());
        // 结束时间
        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间：" + (end - start));
    }

    /**
     * future + ExecutorService 使用
     */
    public static void futureTaskExecutorServiceTest() throws Exception {
        // 开始时间
        long start = System.currentTimeMillis();
        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // futureTask -- 准备凉菜，花费1秒钟
        FutureTask<String> futureTask1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "凉菜准备完成";
            }
        });
        // futureTask -- 准备包子，花费3秒钟
        FutureTask<String> futureTask2 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "包子准备完成";
            }
        });
        // 提交到线程池
        executorService.submit(futureTask1);
        executorService.submit(futureTask2);
        // 获取 futureTask 结果
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        // 结束时间
        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间：" + (end - start));
    }

    /**
     * futureTask + thread 使用，被当作 runnable 使用
     */
    public static void futureTaskThreadTest() throws Exception {
        // 开始时间
        long start = System.currentTimeMillis();
        // FutureTask -- 准备凉菜，花费1秒钟
        FutureTask<String> futureTask1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "凉菜准备完成";
            }
        });
        // FutureTask -- 准备包子，花费3秒钟
        FutureTask<String> futureTask2 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "包子准备完成";
            }
        });
        // 开启两个线程
        new Thread(futureTask1).start();
        new Thread(futureTask2).start();
        // 获取 futureTask 结果
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间：" + (end - start));
    }
}
