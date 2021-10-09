package com.test.future;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 提交 callable 到线程池
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return 1;
            }
        });

        try {
            // 阻塞获取future的值
            Integer result = (Integer) future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}