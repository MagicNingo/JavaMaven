package com.bus.thread;
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
 */
class Window01 extends Thread {
    private static int ticket = 100;
    private static String str = new String();
    private static boolean  f = true;
    //存在线程安全问题
    @Override
    public void run() {
        while(f) {
            show();
            /*synchronized (Window01.class) {
            //synchronized (str) {
                if (ticket > 0) {
                    System.out.println("Window01:"+getName() + ": 买票,票号为:" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }*/
        }
    }

    private static synchronized void show() {
        if (ticket > 0) {
            System.out.println("Window02:"+Thread.currentThread().getName()+": 买票,票号为:"+ticket);
            ticket--;
        } else  {
            f = false;
        }
    }
}

public class WindowTest01 {
    public static void main(String[] args) {
        Window01 w1 = new Window01();
        Window01 w2 = new Window01();
        Window01 w3 = new Window01();
        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");
        w1.start();
        w2.start();
        w3.start();
    }
}
