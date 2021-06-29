package com.yf.controller;

import com.yf.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestController {

    @PostMapping("/user")
    public String addUserInfo(@Valid @RequestBody User user) {
        return "调用成功!";
    }

}