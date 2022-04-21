package com.bus.thread;

/**
 * 多线程的创建 方式二:
 *     1) 定义子类，实现Runnable接口。
 *     2) 子类中重写Runnable接口中的run方法。
 *     3) 通过Thread类含参构造器创建线程对象。
 *     4) 将Runnable接口的子类对象作为实际参数传递给Thread类的构造器中。
 *     5) 调用Thread类的start方法：开启线程，调用Runnable子类接口的run方法。
 */

class MyThread02 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                System.out.println("MyThread02:"+Thread.currentThread().getName() + ": " + i);
            }
        }
    }

    public MyThread02() {
    }

}


public class ThreadTest02 {
    public static void main(String[] args) {
        //通过Thread类含参构造器创建线程对象。
        //MyThread02 mt = new MyThread02();
        //Thread thread = new Thread(mt);
        //thread.start();



        //线程死锁
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        new Thread(){
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();


        new Thread(new Runnable(){
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append("3");
                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");
                        System.out.println(s1);
                        System.out.println(s2);
                    }

                }
            }
        }).start();

    }
}
