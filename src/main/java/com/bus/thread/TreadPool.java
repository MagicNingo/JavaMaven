package com.bus.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的第四种方式: 创建线程池 Executors.newFixedThreadPool
 */
class NumberThread01 implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
class NumberThread02 implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}


public class TreadPool {
    public static void main(String[] args) {
        //提供指定线程池的数量
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //设置线程的属性
        //ThreadPoolExecutor tp = (ThreadPoolExecutor) pool;
        //tp.setCorePoolSize(15);
        //pool.execute(new NumberThread01());//适合使用于Runnable
        //pool.submit(new NumberThread02());//适合使用于Callable
        //pool.shutdown();

        pool.submit(() -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "aaa--->" + i);
            }
        });
        pool.execute(() -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "bbb--->" + i);
            }
        });
    }
}
