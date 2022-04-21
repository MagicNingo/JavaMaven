package com.bus.test;

import java.util.ArrayList;
import java.util.LinkedList;

public class test {
    public static void main(String[] args) {

        //ArrayList<Integer> list = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        long l1 = System.currentTimeMillis();
        list.add(2, 123);
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);

    }
}
