package ru.otus;

import com.google.common.base.Joiner;


public class HelloOtus {
    public static void main(String[] args) {
        String[] strings = new String[]{"Гарри", null, "Дамблдор", "Арагорн"};

        System.out.println(join(strings));

    }
    public  static  String join(String[] strings){
        Joiner joiner = Joiner.on(",").skipNulls();
        return joiner.join(strings[0], strings[1], strings[2], strings[3]);
    }
}
