package com.bus.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestConsumer {

    public static String userSupplier(Supplier<String> s) {
        return s.get();
    }

    public static void userConsumer(String[] str, Consumer<String> c1, Consumer<String> c2) {
        for (String s : str) {
            c1.andThen(c2).accept(s);
        }
    }

    public static void main(String[] args) {
        /*String s = userSupplier(() -> "张三");
        System.out.println(s);*/
        /*String str = "hello,java";*/

        String[] arr = {"林青霞,30", "张曼玉,35", "王祖贤,33"};
        userConsumer(
            arr,
            s -> System.out.print("姓名:"+s.split(",")[0]+",") ,
            s -> System.out.println("年龄:"+s.split(",")[1])
        );







    }
}
