package lc.sz1288;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
 * <p>
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 * <p>
 * Example 1:
 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 */
public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        List<Number> range = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> list = nums.get(i);
            binaryAdd(range, new Number(i, 0, list.get(0)));
        }
        int[] res = new int[2];
        int minRange = Integer.MAX_VALUE;
        while (range.size() == nums.size()) {
            int start = range.get(0).num;
            int end = range.get(range.size() - 1).num;
            int size = end - start;
            if (size < minRange) {
                res = new int[]{start, end};
                minRange = size;
            }
            Number number = range.remove(0);
            if (++number.idx < nums.get(number.id).size()) {
                number.num = nums.get(number.id).get(number.idx);
                binaryAdd(range, number);
            }
        }
        return res;
    }

    private void binaryAdd(List<Number> range, Number num) {
        int index = Collections.binarySearch(range, num);
        if (index < 0) {
            index = -index - 1;
        }
        range.add(index, num);
    }

    @ToString
    private static class Number implements Comparable<Number> {
        int id;
        int idx;
        int num;

        public Number(int id, int idx, int num) {
            this.id = id;
            this.idx = idx;
            this.num = num;
        }

        @Override
        public int compareTo(Number o) {
            return num - o.num;
        }
    }

    public static void main(String[] args) {
        SmallestRange sr = new SmallestRange();
        int[] res1 = sr.smallestRange(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3)
        ));
        System.out.println(Arrays.toString(res1));
    }
}
