package com.test.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 这段代码是一个使用Guava库中的RateLimiter实现令牌桶算法的示例。RateLimiter可以用于限制某个操作的频率，这里的RateLimiter创建了一个每秒最多获取20个令牌的限流器。
 *
 * 在主函数中，我们首先创建了一个RateLimiter对象，然后尝试获取一个令牌。如果成功获取到令牌，就输出"Token pass"；如果获取失败，就输出"Token refuse"。
 *
 * 通过使用RateLimiter，我们可以控制某个操作的频率，以避免系统过载或资源浪费。
 */
public class RateLimiterTest {
    public static void main(String[] args) {
        // 创建一个每秒最多获取20个令牌的RateLimiter
        RateLimiter rateLimiter = RateLimiter.create(20.0);

        // 尝试获取一个令牌
        boolean token = rateLimiter.tryAcquire();

        // 根据获取令牌的结果输出相应的信息
        if (token) {
            System.out.printf("Token pass");
        } else {
            System.out.printf("Token refuse");
        }
    }
}
