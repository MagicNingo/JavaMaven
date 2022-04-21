package com.bus.mythread;

class SumThread implements Runnable {
    private int sum = 0;
    private int n = 1;

    @Override
    public void run() {
        while ( true ) {
            synchronized (this) {
                if (n <= 100) {
                    sum += n;
                    System.out.println(Thread.currentThread().getName() + "第" + n + "计算, 当前值为：" + sum);
                    n++;
                } else {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public int getSum() {
        return sum;
    }
}

public class TestSumThread {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        Thread thread01 = new Thread(sumThread, "线程01: ");
        Thread thread02 = new Thread(sumThread, "线程02: ");
        Thread thread03 = new Thread(sumThread, "线程03: ");

        thread01.start();
        thread02.start();
        thread03.start();

        while (thread01.isAlive() || thread02.isAlive() || thread03.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int sum = sumThread.getSum();
        System.out.println("最终结果是: " + sum);
    }
}

