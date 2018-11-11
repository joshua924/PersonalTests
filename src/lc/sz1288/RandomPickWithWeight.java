package lc.sz1288;

import java.util.Random;
import java.util.TreeMap;

/**
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
 * <p>
 * Note:
 * <p>
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 * Example 1:
 * <p>
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 * Example 2:
 * <p>
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 */
public class RandomPickWithWeight {
    private final TreeMap<Integer, Integer> picks;
    private final Random random;
    private final int bound;

    public RandomPickWithWeight(int[] w) {
        picks = new TreeMap<>();
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            int weight = w[i];
            sum += weight;
            picks.put(sum, i);
        }
        random = new Random();
        bound = sum;

        System.out.println(picks);
    }

    public int pickIndex() {
        return picks.ceilingEntry(random.nextInt(bound) + 1).getValue();
    }

    public static void main(String[] args) {
        RandomPickWithWeight rp = new RandomPickWithWeight(new int[]{20, 5, 1});
        for (int i = 0; i < 100; i++) {
            System.out.print(rp.pickIndex() + " ");
        }
    }
}
