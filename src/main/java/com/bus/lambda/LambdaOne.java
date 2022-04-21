package com.bus.lambda;

import java.util.stream.IntStream;

public class LambdaOne {
    ThreadLocal<Integer> threadLocal;

    public static void main(String[] args) {
        int[] arr = {11, 13,233,243,110};
        getMax(arr);

    }

    public static void getMax(int[] arr) {
        //parallel():并行流
        int max = IntStream.of(arr).parallel().max().getAsInt();
        System.out.println(max);
    }
}
