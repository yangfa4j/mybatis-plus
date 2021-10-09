package com.test.future;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        FutureTask<String> stringFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello";
            }
        });

        Future<?> submit = executorService.submit(stringFutureTask);
        System.out.println("submit.get() = " + submit.get());


    }
}