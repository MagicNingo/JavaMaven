package com.bus.thread;

/**
 * 多线程的创建 方式一:
 *      1) 定义子类继承Thread类。
 *      2) 子类中重写Thread类中的run方法。
 *      3) 创建Thread子类对象，即创建了线程对象。
 *      4) 调用线程对象start方法：启动线程，调用run方法。
 */
class MyThread01 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                System.out.println("MyThread01:"+Thread.currentThread().getName()+": "+i);
            }
            if (i % 30 == 0) {
                yield();//释放cpu执行权
            }
        }
    }

    public MyThread01() {
    }

    public MyThread01(String name) {
        super(name);
    }
}


public class ThreadTest01 {
    public static void main(String[] args) {
        Thread thread = new MyThread01();
        thread.start();
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+": "+i+"======main=======");
        }
    }
}
