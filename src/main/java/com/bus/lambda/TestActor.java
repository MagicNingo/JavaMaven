package com.bus.lambda;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TestActor {

    public static void one() {
        ArrayList<String> manList = new ArrayList<>();
        manList.add("李连杰");
        manList.add("周润发");
        manList.add("成龙");
        manList.add("吴京");
        manList.add("刘德华");
        manList.add("周星驰");

        ArrayList<String> womanList = new ArrayList<>();
        womanList.add("林青霞");
        womanList.add("张曼玉");
        womanList.add("王祖贤");
        womanList.add("林心如");
        womanList.add("张敏");
        womanList.add("林志玲");


        Stream<String> man = manList.stream().filter(
                s -> s.length() == 3
        ).limit(3);

        Stream<String> woman = womanList.stream().filter(
                s -> s.startsWith("林")
        ).skip(1);

        Stream.concat(man,woman).map(Actor::new).forEach(System.out::println);
    }

    public static void main(String[] args) {
        one();
    }
}
