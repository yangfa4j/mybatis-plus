package com.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 运行时有用
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// 作用在类和方法上
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Writer {

    String name();

    int age();

}