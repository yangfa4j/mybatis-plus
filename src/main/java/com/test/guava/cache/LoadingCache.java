package com.test.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 与上面例子唯一的不同就是没有在build的时候传入CacheLoader，而是在cache.get使用Cache的时候用传入Callable对象。
 *
 * 这样做可以灵活配置每次获取的缓存源不一样，但是两种方案都各有好处，大家再使用的时候可以酌情选择哈~。
 */

public class LoadingCache {
    public static void main(String[] args) {
        com.google.common.cache.LoadingCache<String, List<String>> loadingCache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS) //缓存我们设置3秒过期，所以两次Sleep以后就会重新获取数据库。
                .removalListener(notification -> System.out.println("remove key : " + notification.getKey()))
                .build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String key) throws Exception {
                        return MockDB.getFromDB(key);
                    }
                });
        try {
            System.out.println("load from cache once : " + loadingCache.get("0101"));
            Thread.sleep(2000);
            System.out.println("load from cache two : " + loadingCache.get("0101"));
            Thread.sleep(2000);
            System.out.println("load from cache three : " + loadingCache.get("0101"));
            Thread.sleep(2000);
            System.out.println("load not exist key from cache : " + loadingCache.get("0103"));

        } catch (ExecutionException | InterruptedException e) {
            //记录日志
        }
    }
}

 