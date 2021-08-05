package com.yf.gc;

import java.util.HashMap;
import java.util.Map;

public class GCTest {

    public static void main(String[] args) {
        Map<Integer, String> namesMap = new HashMap<>();
        namesMap.put(1, "Larry");
        namesMap.put(2, "Steve");
        namesMap.put(3, "James");
        namesMap.forEach((key, value) -> System.out.println("key = " + key + ",valie = " + value));

        namesMap.keySet().forEach(System.out::println);
        namesMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}