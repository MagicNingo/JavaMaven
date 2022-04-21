package com.bus.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的第三种方式: 实现Callable接口
 */

class NumThread implements Callable {

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
public class CallableTest {
    public static void main(String[] args) {
        NumThread t = new NumThread();
        FutureTask task = new FutureTask(t);
        new Thread(task).start();
        try {
            Object sum = task.get();
            System.out.println("总和为:"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
