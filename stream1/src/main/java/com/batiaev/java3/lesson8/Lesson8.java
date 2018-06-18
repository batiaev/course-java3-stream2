package com.batiaev.java3.lesson8;

public class Lesson8 {
    public static void main(String[] args) {
        Long a = 111L;
        Long b = 111L;
        Long c = 222L;
        Long d = 222L;
        Long e = 112L;

        System.out.println(a == b);
        System.out.println(c == d);
        b = b + 1;
        System.out.println(a);
        System.out.println(b == e);

    }
}
