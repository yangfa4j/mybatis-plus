package com.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yangfa
 * @date 2024/9/27 16:22
 * IntelliJ IDEA
 * @description
 */
public class Test {


    public static void main(String[] args) {
        List<User> list = InitUsers();

        Map<String, List<String>> map = list.stream()
                .collect(Collectors.groupingBy(
                        User::getName,
                        Collectors.mapping(User::getAddress, Collectors.toList())));

        Map<String, List<String>> groupedAddresses = list.stream()
                .collect(Collectors.groupingBy(
                        User::getName,
                        Collectors.mapping(User::getAddress, Collectors.toList())
                ));




    }

    public static List<User> InitUsers() {
        List<User> list = new ArrayList<>();
        list.add(new User("张三", 20, "北京市"));
        list.add(new User("李四", 25, "上海市"));
        list.add(new User("王五", 30, "广州市"));
        list.add(new User("赵六", 35, "深圳市"));
        return list;
    }
}
