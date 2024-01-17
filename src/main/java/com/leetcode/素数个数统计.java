package com.leetcode;

import org.springframework.util.StopWatch;

/**
 * @Date 2023/8/7
 * @Author yangfa
 * @Description 统计n以内的素数个数 素数：只能被1和自身整除的数，0，1 除外
 * <p>
 * 例：输入： 100 输出：25 方法：埃筛法，减少判断次数
 */
public class 素数个数统计 {
    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

//        int i = bf(100);
        int i = leetCode(1000);
        stopWatch.stop();


        System.out.println(i);
        System.out.println(stopWatch.prettyPrint());

    }

    // 暴力算法，循环判断每个数是不是素数
    public static int bf(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }

    // 埃氏筛除法
    public static int leetCode(int n) {
        boolean[] arr = new boolean[n]; // 所有数合集  false 代表素数  true 代表合数
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!arr[i]) {
                count++;
                // 合数判断
                for (int j = i * i; j < n; j += i) { // 4 6 8 10 12
                    arr[j] = true;
                }
            }

        }
        return count;
    }


    // 判断素数不用每个都判断，判断到一半即可
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
