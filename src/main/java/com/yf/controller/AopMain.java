package com.yf.controller;

import com.yf.aop.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/11/23
 * @Author yangfa
 * @Description
 */
@RestController
@RequestMapping("/aop")
public class AopMain {

    @Autowired
    private HelloWorld helloWorld;

    @GetMapping("/hello")
    public void sayHello(@RequestParam(value = "name") String name) {
        helloWorld.sayName(name);
    }
}
