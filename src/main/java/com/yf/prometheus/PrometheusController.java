package com.yf.prometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;

import java.util.concurrent.atomic.DoubleAdder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试接口，验证自定义指标
 */
@RestController()
@RequestMapping("/prometheus")
public class PrometheusController {

    @Resource
    private Counter testApiCounter;

    @Resource
    private DoubleAdder activeRequests;

    @Resource
    private Timer requestTimer;

    @Resource  
    private DistributionSummary requestSizeSummary;

    @GetMapping("/test")
    public String test() {
        // 每次调用接口，计数器 +1
        testApiCounter.increment();
        return "Hello Prometheus!";
    }

    @GetMapping("/active")
    public String active() {
        // 模拟活跃请求数变化
        double value = Math.random() * 100;
        activeRequests.reset();
        activeRequests.add(value);
        return "Active requests updated!";
    }

    @GetMapping("/timer")
    public String timer() {
        // 测量请求处理时间
        return requestTimer.record(() -> {
            // 模拟耗时操作
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Timer recorded!";
        });
    }

    @GetMapping("/summary")
    public String summary() {
        // 统计随机数据大小
        long size = (long) (Math.random() * 1000);
        requestSizeSummary.record(size);
        return "Summary recorded: " + size;
    }
}