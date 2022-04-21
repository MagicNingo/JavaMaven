package com.bus.thread;

public class ThreadMethod {
    public static void main(String[] args) {
        MyThread01 m = new MyThread01("线程二");
        //m.setName("线程一");
        m.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("主线程:" + i);
        }
    }
}
