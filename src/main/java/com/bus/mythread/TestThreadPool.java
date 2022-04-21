package com.bus.mythread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NumberThread01 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            Thread.currentThread().setName("线程一");
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}

class NumberThread02 implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 200; i++) {
            Thread.currentThread().setName("线程二");
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
    }
}
public class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        pool.submit(new NumberThread01());
        pool.submit(new NumberThread02());
        pool.shutdown();
    }
}
