package com.yf.sls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTest {
    // 1. 获取Logger实例（必须和当前类绑定，新手不要改这里）
    private static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    public static void main(String[] args) {
        // 2. 主动输出不同级别的日志（至少输出1条，否则看不到效果）
        System.out.println("=== 开始测试日志输出 ==="); // 先打印一行普通内容，确认程序能运行
        logger.info("这是INFO级别的日志（常规运行信息）");
        logger.warn("这是WARN级别的日志（警告信息）");
        logger.error("这是ERROR级别的日志（错误信息）");
        System.out.println("=== 日志测试结束 ===");
    }
}