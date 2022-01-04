package com.yf.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动记录日志的注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})//作用范围，类、方法
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface AutoLog {

}