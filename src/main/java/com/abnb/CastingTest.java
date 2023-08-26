package com.abnb;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * In Java, it's ok to cast to an object even if the value is null. However, casting to a primitive will result in
 * NPE if the value is null. So we should cast to objects when the value could be null.
 */
public class CastingTest {
    public static void main(String[] args) {
        Map<Integer, Object> objectMap = ImmutableMap.of(
                1, true,
                2, false,
                3, "random"
        );

        try {
            Boolean a = (Boolean) objectMap.get(1);
            System.out.println("a is " + a);
            Boolean b = (Boolean) objectMap.get(4);
            System.out.println("b is " + b);
            boolean c = (boolean) objectMap.get(5);
            System.out.println("c is " + c);
        } catch (Exception e) {
            System.out.println("failed");
        }
    }
}
