package com.bus.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式:
 *      1.方式一: 同步代码块
 *      synchronized(同步监视器) {}
 *      同步监视器: 俗称锁, 任何一个类的对象,都可以充当锁
 *          要求: 多个线程必须要共用一同把锁
 *
 *      2.方式二: 同步方法
 *      在方法返回值前添加synchronized
 *
 *      3.方式三: Lock锁 jdk1.5新增
 *          Lock是显式锁（手动开启和关闭锁，别忘记关闭锁），synchronized是
 *          隐式锁，出了作用域自动释放
 *
 */
class Window03 implements Runnable {
    private int ticket = 100;
    private static boolean  f = true;
    private ReentrantLock lock = new ReentrantLock();
    //存在线程安全问题
    @Override
    public void run() {
        while(f) {
            try {
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Window03:"+Thread.currentThread().getName() + ": 买票,票号为:" + ticket);
                    ticket--;
                } else {
                    break;
                }

            } finally {
                lock.unlock();
            }
        }
    }

}

public class WindowTest03 {
    public static void main(String[] args) {
        Window03 w = new Window03();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();

    }
}
