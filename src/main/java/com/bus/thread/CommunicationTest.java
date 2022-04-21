package com.bus.thread;

/**
 * 线程通信: 使用两个线程打印1-100 ,线程一,二交替打印
 * wait(): 调用wait()方法会阻塞进程, 但同时会释放同步监视器
 * notify(): 唤醒正在排队等待同步资源的线程中优先级最高者结束等待
 * notifyAll(): 唤醒正在排队等待资源的所有线程结束等待
 * 这三个方法只有在synchronized方法或synchronized代码块中才能使用，否则会报
 * java.lang.IllegalMonitorStateException异常。
 */
class Number implements Runnable {
    private int number = 1;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //调用wait()方法阻塞进程,同时释放同步监视器
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }

        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Number n = new Number();
        Thread t1 = new Thread(n);
        Thread t2 = new Thread(n);
        t1.setName("线程一");
        t2.setName("线程二");
        t1.start();
        t2.start();

    }
}
