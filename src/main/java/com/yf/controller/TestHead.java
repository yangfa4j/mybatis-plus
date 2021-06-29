package com.yf.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Set;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YF
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/dbFullPullRequests")
public class TestHead {

    @PostMapping("/header")
    public void test(HttpServletRequest request) {

        String header = request.getHeader("Accept-Encoding");
        if (header!=null) {
            System.out.println(header);
            System.out.println(header.contains("gzip"));
        }

        System.out.println("==========");
        Enumeration<String> headers = request.getHeaders("Accept-Encoding");


        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.get();
    }

    @GetMapping("/get")
    public void testGet(@RequestParam(value = "course_id",required = false) Integer courseId,
                        @RequestParam(name = "grade_group_id",required = false) Set<Integer> gradeGroupId) {
        System.out.println(courseId);
        System.out.println(gradeGroupId);
    }
}

