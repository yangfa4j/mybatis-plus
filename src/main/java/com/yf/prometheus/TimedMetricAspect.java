package com.yf.prometheus;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TimedMetricAspect {

    @Autowired
    private MeterRegistry meterRegistry;

    @Around("@annotation(timedMetric)")
    public Object handleTimed(ProceedingJoinPoint pjp, TimedMetric timedMetric) throws Throwable {
        // 1. 准备基础上下文
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getMethod().getName();
        String className = signature.getDeclaringType().getSimpleName();
        String metricName = timedMetric.value().isEmpty() ? "method.execution.time" : timedMetric.value();

        // 组合基础标签：类名、方法名
        String[] baseTags = new String[]{
                "class", className,
                "method", methodName
        };

        // 2. 执行业务逻辑并计时
        Timer.Sample sample = Timer.start(meterRegistry);
        Object result;

        try {
            result = pjp.proceed(); // 执行业务方法
            return result;
        } catch (Throwable e) {
            // 3. 异常处理
            // 记录错误计数器
            String[] errorTags = Arrays.copyOf(baseTags, baseTags.length + 2);
            errorTags[baseTags.length] = "status";
            errorTags[baseTags.length + 1] = "error";

            Counter.builder(metricName + ".error")
                    .tags(errorTags)
                    .register(meterRegistry)
                    .increment();

            // 重新抛出异常，确保业务逻辑的异常不被吞掉
            throw e;
        } finally {
            // 4. 记录耗时 (无论成功还是失败都要记录执行时间)
            long duration = sample.stop(
                    Timer.builder(metricName)
                            .tags(baseTags)
                            .register(meterRegistry)
            );
        }
    }
}
