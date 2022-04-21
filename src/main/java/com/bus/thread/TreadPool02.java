package com.bus.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TreadPool02 {
    public static void main(String[] args) {
        //提供指定线程池的数量
        ExecutorService pool = Executors.newFixedThreadPool(10);


        Future<String> future = pool.submit(() -> {  // Callable的实现类，是有返回值的
            System.out.println(Thread.currentThread().getName() + "--aaa--->");
            return "hello, aaa";
        });

        Future<String> stringFuture = pool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "--bbb--->");
            return "hello, bbb";
        });

        try {
            System.out.println(future.get());
            System.out.println(stringFuture.get());
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
