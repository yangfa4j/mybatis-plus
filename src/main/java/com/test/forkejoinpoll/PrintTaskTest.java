package com.test.forkejoinpoll;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2020/10/21
 * @Author yangfa
 * @Description
 */
public class PrintTaskTest {

    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new PrintTask(0, 200));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);// 阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        // 关闭线程池
        forkJoinPool.shutdown();
    }
}
