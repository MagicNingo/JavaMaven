package com.bus.thread;
/**
 * 实现方式的优点:
 * 1.避免了单继承的局限性
 * 2.多个线程可以共享同一个接口实现类的对象，非常适合多个相同线
 * 程来处理同一份资源。
 */

/**
 * 解决线程安全问题的方式:
 *      1.方式一: 同步代码块
 *      synchronized(同步监视器) {}
 *      同步监视器: 俗称锁, 任何一个类的对象,都可以充当锁
 *          要求: 多个线程必须要共用一同把锁
 *
 *      2.方式二: 同步方法
 *
 */
class Window02 implements Runnable {
    private int ticket = 100;
    //String str = new String();
    boolean f = true;
    @Override
    public void run() {
        while(f) {
            show();
            //Runnable方式的同步监视器可以用this代替
            /*synchronized(this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Window02:"+Thread.currentThread().getName()+": 买票,票号为:"+ticket);
                    ticket--;
                } else {
                    break;
                }
            }*/
        }
    }
    private synchronized void show() {
        if (ticket > 0) {
            System.out.println("Window02:"+Thread.currentThread().getName()+": 买票,票号为:"+ticket);
            ticket--;
        } else  {
            f = false;
        }
    }
}


public class WindowTest02 {
    public static void main(String[] args) {
        Window02 w = new Window02();
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
