package ru.dvvar.graduate;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class Test {
    public static void main(String[] args) {
        Object o = new A().get();
    }

    public static String get() {
        return "asdf";
    }

    public static class A {

        public String get() {
            return "asdf";
        }
    }
}
