package com.test.forkejoinpoll;

import java.util.concurrent.RecursiveAction;

/**
 * @Date 2020/10/21
 * @Author yangfa
 * @Description 没有返回值
 */
public class PrintTask extends RecursiveAction {

    // 每个"小任务"最多只打印50个数
    private static final int MAX = 50;

    private Integer max;
    private Integer min;

    public PrintTask(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        if (max - min < MAX) {
            for (int i = min; i < max; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值:" + i);
            }
        } else {
            // 将大任务分解成两个小任务
            int middle = (min + max) / 2;
            PrintTask left = new PrintTask(min, middle);
            PrintTask right = new PrintTask(middle, max);
            // 并行执行两个小任务
            left.fork();
            right.fork();
        }
    }
}
