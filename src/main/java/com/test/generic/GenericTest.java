package com.test.generic;

/**
 * @Date 2021/6/25
 * @Author yangfa
 * @Description
 */
public class GenericTest {
    public static void main(String[] args) {

        Generic<String> str = new Generic<>("总有刁民想害朕");
        Generic<Integer> integer = new Generic<>(110);
        Generic<Boolean> b = new Generic<>(true);

        System.out.println("传入类型：" + str.getName() + "  " + integer.getName() + "  " + b.getName());

        str.m1(new Generic<>("我是你爸爸"));

        str.m2(111,"sdasd");
    }
}
