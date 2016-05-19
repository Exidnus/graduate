package ru.dvvar.graduate;

import java.util.ArrayList;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new ArrayList<String>().getClass() == new ArrayList<String>().getClass());
        String s = "!!!123asdf 111";
        String s1 = s.replaceAll("[\\p{Punct}[\\d]]", "");
        System.out.println(s1);
    }
}
