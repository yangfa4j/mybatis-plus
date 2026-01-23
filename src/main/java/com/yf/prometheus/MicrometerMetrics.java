package com.yf.prometheus;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

/**
 *  Micrometer 指标工具类
 * 提供了 Counter, Timer, Gauge, Summary, LongTaskTimer 的完整封装
 */
@Component
public class MicrometerMetrics {

    private final MeterRegistry meterRegistry;

    public MicrometerMetrics(MeterRegistry meterRegistry) {
        Assert.notNull(meterRegistry, "MeterRegistry must not be null");
        this.meterRegistry = meterRegistry;
    }

    // ====================================================================================================
    // 1. Counter (计数器): 只增不减，用于统计总量
    // ====================================================================================================

    /**
     * 累加计数
     */
    public void count(String name, String... tags) {
        getCounter(name, tags).increment();
    }

    /**
     * 指定增量
     */
    public void count(String name, double amount, String... tags) {
        getCounter(name, tags).increment(amount);
    }

    private Counter getCounter(String name, String... tags) {
        return Counter.builder(name)
                .tags(tags)
                .register(meterRegistry);
    }

    // ====================================================================================================
    // 2. Timer (计时器): 统计耗时和频率
    // ====================================================================================================

    /**
     * 执行代码块并计时 (泛型返回值)
     */
    public <T> T time(String name, Supplier<T> block, String... tags) {
        return time(name, block, null, tags);
    }

    /**
     * 执行代码块并计时，支持异常处理回调
     */
    public <T> T time(String name, Supplier<T> block, Consumer<Throwable> errorHandler, String... tags) {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            T result = block.get();
            sample.stop(Timer.builder(name).tags(tags).register(meterRegistry));
            return result;
        } catch (Throwable t) {
            if (errorHandler != null) {
                errorHandler.accept(t);
            }
            // 注意：这里不记录耗时，或者记录为异常结束，取决于业务需求
            throw t;
        }
    }

    /**
     * 手动记录耗时
     */
    public void recordTime(String name, long duration, TimeUnit unit, String... tags) {
        Timer timer = Timer.builder(name)
                .tags(tags)
                .register(meterRegistry);
        timer.record(duration, unit);
    }

    // ====================================================================================================
    // 3. Gauge (仪表盘): 反映实时状态 (如内存、队列长度)
    // 注意：Gauge 通常用于监控对象的某个属性，不要在这里传入瞬时值。
    // ====================================================================================================

    /**
     * 监控一个对象的实时数值 (例如：监控队列大小)
     * @param name 指标名
     * @param obj 被监控的对象 (如 AtomicInteger, List)
     * @param valueFunction 如何从对象中获取数值的函数 (如 AtomicInteger::get)
     * @param tags 标签
     * @param <T> 对象类型
     */
    public <T> void gauge(String name, T obj, ToDoubleFunction<T> valueFunction, String... tags) {
        Gauge.builder(name, obj, valueFunction)
                .tags(tags)
                .register(meterRegistry);
    }

    // ====================================================================================================
    // 4. DistributionSummary (分布摘要): 统计事件的大小分布 (如响应体大小)
    // ====================================================================================================

    /**
     * 记录分布摘要
     */
    public void summary(String name, double amount, String... tags) {
        DistributionSummary summary = DistributionSummary.builder(name)
                .tags(tags)
                // 可以在这里配置百分位、SLA 等，也可以在配置文件中统一配置
                .register(meterRegistry);
        summary.record(amount);
    }

    // ====================================================================================================
    // 5. LongTaskTimer (长任务计时器): 用于监控长时间运行的任务 (如批处理)
    // ====================================================================================================

    /**
     * 开始一个长任务计时
     */
    public LongTaskTimer.Sample startLongTask(String name, String... tags) {
        LongTaskTimer timer = LongTaskTimer.builder(name)
                .tags(tags)
                .register(meterRegistry);
        return timer.start();
    }

    // ====================================================================================================
    // 6. 复合操作
    // ====================================================================================================

    /**
     * 便捷方法：成功/失败计数
     */
    public void countSuccess(String baseName, String... tags) {
        count(baseName + ".success", tags);
    }

    public void countError(String baseName, String... tags) {
        count(baseName + ".error", tags);
    }
}