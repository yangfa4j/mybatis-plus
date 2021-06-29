package com.test.lock;

/**
 * @Date 2021/2/4
 * @Author yangfa
 * @Description
 */
public class DeathLock {
    private static String resourse_a = "a";
    private static String resourse_b = "b";

    public static void main(String[] args) {
        deadLock();
    }

    public static void deadLock() {
        Thread thread1 = new Thread(() -> {
            synchronized (resourse_a) {
                System.out.println(" thread1 get resource a");
                try {
                    Thread.sleep(3000);
                    synchronized (resourse_b) {
                        System.out.println("thread1 get resource b");
                    }
                } catch (Exception e) {

                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resourse_b) {
                System.out.println("thread2 get resource b");
                try {
                    Thread.sleep(3000);
                    synchronized (resourse_a) {
                        System.out.println("thread2 get resource a");
                    }
                } catch (Exception e) {

                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
