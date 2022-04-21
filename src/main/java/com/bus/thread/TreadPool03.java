package com.bus.thread;

import java.util.concurrent.*;

public class TreadPool03 {
    public static void main(String[] args) {
      ThreadPoolExecutor executor =  new ThreadPoolExecutor(
               10,         // 核心线程数
               15,     // 核心线程和非核心线程的总数
               10L,       // 线程的空闲时间<默认指的时非核心线程>
               TimeUnit.SECONDS,      //用来描述上一个擦书的单位
               new LinkedBlockingQueue<>(),   // workQueue：存储等待执行的任务
               Executors.defaultThreadFactory(),  //创建的线程有相同的优先级，非守护线程，有线程名
               new ThreadPoolExecutor.AbortPolicy());  // 拒绝任务策略


        // 开启此属性， 代表核心线程
        executor.allowCoreThreadTimeOut(true);
        executor.submit(() -> {
            System.out.println("---- 使用线程池 ----");
        });

        executor.shutdown();
    }
}
