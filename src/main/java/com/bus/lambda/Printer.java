package com.bus.lambda;

@FunctionalInterface
//函数是接口只能有一个抽象方法
public interface Printer {
    void print(String s);

    //default方法对象调用
    default void one() {
        System.out.println("--- default ---");
    }
    //静态方法类名调用
    static void test() {
        System.out.println("--- 静态方法 ---");
    }
}
