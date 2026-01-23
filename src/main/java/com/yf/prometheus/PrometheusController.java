package com.yf.prometheus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试接口，验证自定义指标
 */
@RestController()
@RequestMapping("/prometheus")
public class PrometheusController {

    @Resource
    private MicrometerMetrics metrics;

    private static AtomicInteger atomicInt = new AtomicInteger(0);

    /**
     * 测试 Counter 增量功能
     */
    @GetMapping("/counter/increment")
    public String testCounterIncrement() {
        metrics.count("micrometer.metrics.test.counter", "type", "increment");
        return "Counter increment test completed";
    }

    /**
     * 测试 Counter 指定增量功能
     */
    @GetMapping("/counter/amount")
    public String testCounterWithAmount() {
        int randomIncrement = 1 + (int) (Math.random() * 100);
        metrics.count("micrometer.metrics.test.counter.amount", randomIncrement, "type", "amount");
        return "Counter with amount test completed add：" + randomIncrement;
    }

    /**
     * 测试 Timer 带返回值的计时功能
     */
    @GetMapping("/timer/supplier")
    public String testTimerWithSupplier() {
        String result = metrics.time("micrometer.metrics.test.timer",
                () -> {
                    // 修改为随机等待时间
                    int randomDelay = 1000 + (int) (Math.random() * 4000); // 1s到5s之间的随机毫秒数
                    try {
                        Thread.sleep(randomDelay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "test_result";
                },
                "type", "supplier"
        );
        return "Timer with supplier test completed, result: " + result;
    }

    /**
     * 测试 Timer 异常处理回调功能
     */
    @GetMapping("/timer/error-handler")
    public String testTimerWithErrorHandler() {
        try {
            metrics.time("micrometer.metrics.test.timer.error",
                    () -> {
                        throw new RuntimeException("Test exception");
                    },
                    throwable -> System.out.println("Caught exception: " + throwable.getMessage()),
                    "type", "error_handler"
            );
        } catch (Exception e) {
            System.out.println("Expected exception caught: " + e.getMessage());
        }
        return "Timer with error handler test completed";
    }

    /**
     * 测试手动记录时间功能
     */
    @GetMapping("/timer/manual")
    public String testRecordTime() {
        metrics.recordTime("micrometer.metrics.test.manual.timer", 100, java.util.concurrent.TimeUnit.MILLISECONDS, "type", "manual");
        return "Manual record time test completed";
    }

    /**
     * 测试 Gauge 仪表盘功能
     */
    @GetMapping("/gauge")
    public String testGauge() {
        // 随机变化值
        int newValue = 1 + (int) (Math.random() * 100);
        atomicInt.set(newValue);
        metrics.gauge("micrometer.metrics.test.gauge", atomicInt, AtomicInteger::get, "type", "gauge");
        return "Gauge test completed, current value: " + atomicInt.get();
    }

    /**
     * 测试 Summary 分布摘要功能
     */
    @GetMapping("/summary")
    public String testSummary() {
        // 生成1-100之间的随机数
        double randomValue1 = 1 + (int) (Math.random() * 100);
        double randomValue2 = 1 + (int) (Math.random() * 100);

        metrics.summary("micrometer.metrics.test.summary", randomValue1, "type", "summary");
        metrics.summary("micrometer.metrics.test.summary", randomValue2, "type", "summary");

        return "Summary test completed, values: " + randomValue1 + ", " + randomValue2;
    }


    /**
     * 测试 LongTaskTimer 长任务计时功能
     */
    @GetMapping("/long-task-timer")
    public String testLongTaskTimer() {
        io.micrometer.core.instrument.LongTaskTimer.Sample sample = metrics.startLongTask("micrometer.metrics.test.long.task", "type", "long_task");
        if (sample != null) {
            // 模拟任务执行后停止
            sample.stop();
        }
        return "Long task timer test completed";
    }

    /**
     * 测试成功/失败计数功能
     */
    @GetMapping("/count/success-error")
    public String testCountSuccessAndError() {
        metrics.countSuccess("micrometer.metrics.test.operation", "type", "success_error");
        metrics.countError("micrometer.metrics.test.operation", "type", "success_error");
        return "Success and error count test completed";
    }

    /**
     * 测试 TimedMetric 注解的使用
     */
    @TimedMetric(value = "yf.test.method.time", tags = {"method", "test"})
    @GetMapping("/timed-test")
    public String timedTest() {
        // 模拟业务逻辑耗时
        try {
            Thread.sleep(100 + (int) (Math.random() * 500)); // 100-600ms 随机等待
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Timed test completed";
    }

    /**
     * 测试异常情况下的 TimedMetric 注解
     */
    @TimedMetric(value = "yf.test.method.error.time", tags = {"method", "test"})
    @GetMapping("/timed-test-error")
    public String timedTestWithError() {
        // 模拟业务逻辑耗时
        try {
            Thread.sleep(100 + (int) (Math.random() * 500)); // 100-600ms 随机等待

            // 模拟业务异常
            if (Math.random() > 0.5) {
                throw new RuntimeException("Simulated business exception");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Timed test with error completed";
    }

    /**
     * 测试 CounterMetric 注解的使用
     */
    @CounterMetric(value = "yf.test.method.counter", tags = {"method", "test"})
    @GetMapping("/counter-test")
    public String counterTest() {
        // 模拟业务逻辑
        try {
            Thread.sleep(100 + (int) (Math.random() * 500)); // 100-600ms 随机等待
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Counter test completed";
    }




    /**
     * 运行所有测试
     */
    @GetMapping("/all-tests")
    public String runAllTests() {
        // Counter 测试
        metrics.count("micrometer.metrics.test.counter", "type", "increment");

        int randomIncrement = 1 + (int) (Math.random() * 100);
        metrics.count("micrometer.metrics.test.counter.amount", randomIncrement, "type", "amount");


        int randomDelay = 1000 + (int) (Math.random() * 4000); // 1s到5s之间的随机毫秒数
        // Timer 测试
        String timerResult = metrics.time("micrometer.metrics.test.timer",
                () -> {
                    try {
                        Thread.sleep(randomDelay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return "test_result";
                },
                "type", "supplier"
        );

        metrics.recordTime("micrometer.metrics.test.manual.timer", 100, java.util.concurrent.TimeUnit.MILLISECONDS, "type", "manual");

        int newValue = 1 + (int) (Math.random() * 100);
        atomicInt.set(newValue);
        metrics.gauge("micrometer.metrics.test.gauge", atomicInt, AtomicInteger::get, "type", "gauge");

        double randomValue1 = 1 + (int) (Math.random() * 100);
        double randomValue2 = 1 + (int) (Math.random() * 100);
        // Summary 测试
        metrics.summary("micrometer.metrics.test.summary", randomValue1, "type", "summary");
        metrics.summary("micrometer.metrics.test.summary", randomValue2, "type", "summary");

        // LongTaskTimer 测试
        io.micrometer.core.instrument.LongTaskTimer.Sample sample = metrics.startLongTask("micrometer.metrics.test.long.task", "type", "long_task");
        if (sample != null) {
            sample.stop();
        }

        // 便捷方法测试
        metrics.countSuccess("micrometer.metrics.test.operation", "type", "success_error");
        metrics.countError("micrometer.metrics.test.operation", "type", "success_error");

        return "All tests completed! Timer result: " + timerResult;
    }
}