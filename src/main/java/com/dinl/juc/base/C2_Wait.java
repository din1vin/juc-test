package com.dinl.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * @author dingliang
 * @since 2021/4/15
 **/
public class C2_WaitAndNotify {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                synchronized (resourceA) {
                    System.out.println("ThreadA get resourceA lock");
                }
                synchronized (resourceB) {
                    System.out.println("ThreadA get resourceB lock");
                    System.out.println("ThreadA release resourceA lock");
                    resourceA.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                synchronized (resourceA){
                    System.out.println("ThreadB get resourceA lock");
                    System.out.println("ThreadB try to get resourceB lock");
                }
                synchronized (resourceB){
                    System.out.println("ThreadB get resourceB lock");
                    System.out.println("ThreadB release resourceA lock");
                    resourceA.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //启动线程
        threadA.start();
        threadA.start();

        //等待两个线程结束
        threadA.join();
        threadB.join();

        System.out.println("main over");
    }
}
