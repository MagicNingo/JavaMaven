package com.bus.lambda;

public class LambdaThree {
    public static void main(String[] args) {
        testSum(( a, b)-> a - b);
    }

    public static void testSum(AddAble able) {
        System.out.println(able.sum(5,10));
    }
}
