package com.yf.prometheus;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CounterMetric {
    String value(); // 指标名
    String[] tags() default {}; // 额外标签
}
