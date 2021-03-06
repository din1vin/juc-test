package com.dinl.juc.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dingliang
 * @since 2021/4/14
 **/
public class C0_Demo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "C").start();
    }

    public static class Ticket {
        private int ticketNum = 30;
        Lock lock = new ReentrantLock();

        public void sale() {
            lock.lock();
            try {
                if (ticketNum > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + ticketNum-- + "张票,还剩" + ticketNum);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}

