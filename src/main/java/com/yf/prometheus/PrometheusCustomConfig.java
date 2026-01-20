package com.yf.prometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.DoubleAdder;

@Configuration
public class PrometheusCustomConfig {


    // 自定义计数器：统计/test接口的调用次数
    @Bean
    public Counter testApiCounter(MeterRegistry meterRegistry) {
        return meterRegistry.counter(
                "yangfa_test", // 指标名
                "method", "GET",       // 标签：请求方法
                "path", "/test"        // 标签：请求路径
        );
    }

    // 自定义仪表：统计当前活跃请求数
    @Bean
    public DoubleAdder activeRequests(MeterRegistry meterRegistry) {
        DoubleAdder activeRequests = new DoubleAdder();
        meterRegistry.gauge(
                "yangfa_active_requests", // 指标名
                Tags.of("type", "api"),   // 标签：类型
                activeRequests,            // 绑定的数值对象
                DoubleAdder::sum            // 提取值的函数
        );
        return activeRequests;
    }

    // 自定义计时器：统计请求处理时间
    @Bean
    public Timer requestTimer(MeterRegistry meterRegistry) {
        return meterRegistry.timer(
                "yangfa_request_timer",   // 指标名
                "method", "GET",           // 标签：请求方法
                "path", "/timer"           // 标签：请求路径
        );
    }

    // 自定义分布摘要：统计请求数据大小
    @Bean
    public DistributionSummary requestSizeSummary(MeterRegistry meterRegistry) {
        return meterRegistry.summary(
                "yangfa_request_size",     // 指标名
                "type", "data"             // 标签：类型
        );
    }
}
