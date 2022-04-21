package com.bus.lambda;

public interface Human {
     void eating(String food);

     default void sport() {
          System.out.println(" sport ");
     }
}
