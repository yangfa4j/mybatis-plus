package com.yf.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2021/12/20
 * @Author yangfa
 * @Description
 */
@RestController
public class AutoLogController {

    public static void main(String[] args) {
        String str = "http://conf.ctripcorp.com/pages/viewpage.action?pageId=80610322";
        String pattern = "[a-zA-Z]+://\\w+\\.(ctripcorp|ctrip)\\..*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }

    // 自动记录日志
    @AutoLog
    @GetMapping("/add")
    public void test() {
        System.out.println("===========test方法执行============");
    }
}

