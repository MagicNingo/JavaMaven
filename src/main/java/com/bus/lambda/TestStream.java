package com.bus.lambda;

import java.util.*;

public class TestStream {
    public static void one() {
        List<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");

        list.stream().filter(s-> s.startsWith("张")).filter(s -> s.length() >= 3).forEach(System.out::println);
    }
    public static void two() {
        HashMap<String, String> map = new HashMap<>();
        map.put("one","111");
        map.put("two","222");
        map.put("three","333");
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> s : set) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        //one();
        two();
    }
}
