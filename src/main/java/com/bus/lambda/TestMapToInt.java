package com.bus.lambda;

import java.util.ArrayList;

public class TestMapToInt {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("10");
        list.add("20");
        list.add("30");
        list.add("40");
        list.add("50");

        //list.stream().map(Integer::parseInt).forEach(System.out::println);

        int sum = list.stream().mapToInt(Integer::parseInt).sum();
        System.out.println(sum);
    }
}
