package com.yf.prometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CounterMetricAspect {

    @Autowired
    private MeterRegistry meterRegistry;

    @Around("@annotation(counter)")
    public Object handleCounter(ProceedingJoinPoint pjp, CounterMetric counter) throws Throwable {
        // 1. 准备基础上下文
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getDeclaringType().getSimpleName();
        String metricName = counter.value().isEmpty() ? "method.counter" : counter.value();

        // 组合基础标签：类名、方法名
        String[] baseTags = new String[]{
                "class", className,
                "method", methodName
        };

        // 2. 执行业务逻辑并记录计数
        try {
            Object result = pjp.proceed(); // 执行业务方法
            return result;
        } catch (Throwable e) {
            // 记录错误计数器
            String[] errorTags = Arrays.copyOf(baseTags, baseTags.length + 2);
            errorTags[baseTags.length] = "status";
            errorTags[baseTags.length + 1] = "error";

            Counter.builder(metricName + ".error")
                    .tags(errorTags)
                    .register(meterRegistry)
                    .increment();

            throw e;
        } finally {
            // 记录成功计数
            Counter.builder(metricName)
                    .tags(baseTags)
                    .register(meterRegistry)
                    .increment();
        }
    }
}
