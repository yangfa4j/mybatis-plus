package com.test.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * @Date 2020/11/19
 * @Author yangfa
 * @Description java 8 时间类的使用
 */
public class LocalTest {
    public static void main(String[] args) {
        LocalTest localTest = new LocalTest();
        localTest.testLocalDateTime();
        localTest.testLocalDate();
        localTest.testLocalTime();

    }

    public void testLocalDateTime() {
        // 获取当前的日期时间
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("当前日期时间: " + dateTime);

        LocalDate date1 = dateTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = dateTime.getMonth();
        int year = dateTime.getYear();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int seconds = dateTime.getSecond();
        System.out.println("年: " + year + ", 月: " + month + ", 日: " + day + ", 时: " + hour + ", 分: " + minute + ", 秒: " + seconds);

        LocalDateTime date2 = dateTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
        System.out.println();
    }


    public void testLocalDate() {
        // 获取当前的日期,没有时、分、秒了
        LocalDate localDate = LocalDate.now();
        System.out.println("当前日期: " + localDate);
        int year = localDate.getYear();
        Month month = localDate.getMonth();
        int day = localDate.getDayOfMonth();
        System.out.println("年: " + year + ", 月: " + month + ", 日: " + day);
        System.out.println();
    }

    public void testLocalTime() {
        // 获取当前的时间,没有年、月、日了
        LocalTime localTime = LocalTime.now();
        System.out.println("当前时间: " + localTime);
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        int second = localTime.getSecond();
        System.out.println("时: " + hour + ", 分: " + minute + ", 秒: " + second);

    }



}
