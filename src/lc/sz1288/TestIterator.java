package lc.sz1288;

import java.util.Arrays;
import java.util.List;

public class TestIterator {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        test(list, list.iterator().next().getClass());
    }

    private static void test(Iterable<? extends Object> items, Class<?> clazz) {
        for (Object item : items) {
            System.out.println(item);
        }
    }
}
