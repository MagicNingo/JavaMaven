package com.bus.lambda;

public class FunctionReference {
    public static void main(String[] args) {
        //使用lambda表达式
        //useConverter(s ->Integer.parseInt(s));
        //useConverter(s-> MyInteger.myMethod(s));
        //usePrinter(s-> System.out.println(s.toUpperCase()));

        //方法引用(建议使用方法引用)
        //usePrinter(System.out::print);
        //useConverter(MyInteger::myMethod);
        //PrinterString ps = new PrinterString();
        //usePrinter(ps::printerString);

        String s = useMyString(String::substring);
        System.out.println(s);


    }
    public static void useConverter(Converter c) {
        int i = c.convert("22223333");
        System.out.println(i * 2);
    }
    public static void usePrinter(Printer p) {
        p.print("hello,java");
    }

    public static String useMyString(MyString ms) {
        return ms.mySubString("123456", 2, 4);
    }
}
