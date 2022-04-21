package com.bus.test;

public class SolveDeadLock {
    public static void main(String[] args) {
        //创建两个锁对象
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程1业务逻辑
                synchronized(lock1){
                    System.out.println("线程1得到了锁子1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1尝试获取锁2...");
                    synchronized(lock2){
                        System.out.println("线程1获得了锁2!");
                    }
                }
            }
        },"线程1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程2业务逻辑
                synchronized(lock1){
                    System.out.println("线程2得到了锁子1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程2尝试获取锁2...");
                    synchronized(lock2){
                        System.out.println("线程2获得了锁2");
                    }
                }
            }
        },"线程2");
        thread1.start();
        thread2.start();
    }
}

