package lc.sz1288;

import java.util.Arrays;
import java.util.Comparator;

public class LongestChain {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(p -> p[1]));
        int count = 0;
        int currentMax = Integer.MIN_VALUE;
        for (int[] pair : pairs) {
            if (pair[0] > currentMax) {
                currentMax = pair[1];
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LongestChain lc = new LongestChain();
        int[][] pairs = new int[][]{{1, 2}, {2, 3}, {3, 4}, {6, 7}};
        System.out.println(lc.findLongestChain(pairs));
    }
}
