package ru.dvvar.graduate;

import java.math.BigDecimal;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class Test {
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal(255.4).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(decimal);
    }

}
