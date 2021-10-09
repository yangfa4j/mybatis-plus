package com.test.future;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

        futureTest();

    }

    public static void threadTest() throws Exception {
        long start = System.currentTimeMillis();

        // 等凉菜 -- 必须要等待返回的结果，所以要调用join方法
        ColdDishThread coldDishThread = new ColdDishThread();
        coldDishThread.start();
        coldDishThread.join();

        // 等包子 -- 必须要等待返回的结果，所以要调用join方法
        BumThread bumThread = new BumThread();
        bumThread.start();
        bumThread.join();

        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间：" + (end - start));
    }

    public static void futureTest() throws Exception {
        long start = System.currentTimeMillis();
        Callable cal1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "凉菜准备完成";
            }
        };
        FutureTask<String> ft1 = new FutureTask<String>(cal1);
        new Thread(ft1).start();

        Callable cal2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "包子准备完成";
            }
        };
        FutureTask<String> ft2 = new FutureTask<String>(cal2);
        new Thread(ft2).start();

        System.out.println(ft1.get());
        System.out.println(ft2.get());

        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间：" + (end - start));
    }

    // 传递方法参数
    public Consumer<String> consumer() {
        return new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }
        };
    }

    // 传递方法参数
    public Supplier<String> supplier() {
        return new Supplier<String>() {
            @Override
            public String get() {
                return "调用了 Supplier方法";
            }
        };
    }

    public void consumerTest(Consumer<String> consumer, List<String> arrays) {
        for (String array : arrays) {
            consumer.accept(array);
        }
    }

    public void SupplierTest(Supplier<String> stringSupplier, List<String> arrays) {
        for (String array : arrays) {
            stringSupplier.get();
        }
    }


}
