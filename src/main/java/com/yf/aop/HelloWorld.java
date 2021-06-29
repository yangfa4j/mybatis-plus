package com.yf.aop;

import org.springframework.stereotype.Component;

/**
 * @Date 2020/11/23
 * @Author yangfa
 * @Description
 */
@Component
public class HelloWorld {

    @AopTest(age = 18)
    public void sayName(String name) {
        System.out.println("name = " + name);
        System.out.println("测试aop方法！！！");
    }
}
