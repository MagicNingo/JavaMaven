package com.bus.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MiddleOption {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无机");

        Stream<String> limit = list.stream().limit(4);//limit(4)输出前三个
        Stream<String> skip = list.stream().skip(2);//skip(2)跳过前两个

        Stream.concat(limit, skip).distinct().forEach(System.out::println);
        //distinct()去重
    }
}
