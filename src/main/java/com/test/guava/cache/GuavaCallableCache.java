package com.test.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCallableCache {
    public static void main(String[] args) {
        final String key = "0101";
        Cache<String, List<String>> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(notification -> System.out.println("cache expired, remove key : " + notification.getKey()))
                .build();
        try {
            Optional<List<String>> optional;
            System.out.println("load from cache once : " + cache.get(key, () -> MockDB.getFromDB(key)));
            Thread.sleep(2000);
            System.out.println("load from cache twice : " + cache.get(key, () -> MockDB.getFromDB(key)));
            Thread.sleep(2000);
            System.out.println("load from cache third : " + cache.get(key, () -> MockDB.getFromDB(key)));
            Thread.sleep(2000);
            final String nullKey = "0103";
            List<String> strings = cache.get(nullKey, () -> MockDB.getFromDB(nullKey));
            System.out.println("load not exist key from cache : " + strings);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}