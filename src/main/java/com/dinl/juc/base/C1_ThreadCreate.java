package com.dinl.juc.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dingliang
 * @since 2021/4/15
 * 创建线程测试
 **/
public class C1_ThreadCreate {
    public static void main(String[] args) {
        MyThread01 myThread01 = new MyThread01();
        myThread01.start();

        new Thread(new MyThread02()).start();

        FutureTask<String> stringFutureTask = new FutureTask<>(new MyThread03());
        new Thread(stringFutureTask).start();
        try {
            System.out.println(stringFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 继承Thread类
     * 好处: run方法内部的this等同于Thread.currentThread(),方便传参
     * 缺点: 无法继承其他类,任务与代码没有分离,没有返回值
     */
    public static class MyThread01 extends Thread {
        @Override
        public void run() {
            System.out.println("Extends Thread to create");
        }
    }

    /**
     * 实现Runnable接口
     * 好处: 可以用Lambda表达式简化代码,可以继承其他类
     * 缺点: 没有返回值
     */
    public static class MyThread02 implements Runnable {
        @Override
        public void run() {
            System.out.println("Implements Runnable to create");
        }
    }

    /**
     * 实现Callable接口
     * 好处: 能够获取线程执行结果
     */
    public static class MyThread03 implements Callable<String>{
        @Override
        public String call() throws Exception {
            return "Implements Callable to create with returns";
        }
    }
}

