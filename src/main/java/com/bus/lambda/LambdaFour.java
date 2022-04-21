package com.bus.lambda;

public class LambdaFour {
    public static void main(String[] args) {
        new Human() {
            @Override
            public void eating(String food) {
                System.out.println("每天都要吃"+food);
            }
        };
        Human human = s-> System.out.println(s);
        human.sport();
    }

}
