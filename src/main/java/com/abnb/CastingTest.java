package com.abnb;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.BooleanUtils;

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

        Boolean a = (Boolean) objectMap.get(1);
        if (BooleanUtils.isTrue(a)) {
            System.out.println("got it 1");
        }
        Boolean b = (Boolean) objectMap.get(4);
        if (BooleanUtils.isTrue(b)) {
            System.out.println("got it 2");
        }
    }
}
