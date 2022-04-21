package com.bus.lambda;

import java.util.stream.IntStream;

public class LambdaTwo {
    public static void main(String[] args) {
        testPrint(new Printer() {
            @Override
            public void print(String s) {
                System.out.println("打印数据!");
            }
        });
        testPrint(System.out::println);

        //可以使用变量的方式,来接住lambda表达式代表的对象
        Printer p = (s) -> System.out.println(s+"---->");
        p.print("java");
    }

    public static void testPrint(Printer printer) {
        printer.print("-------");
    }
}
