package lc.sz1288;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
  private final Random random;
  private int[] sums;

  public RandomPickWithWeight(int[] w) {
    this.sums = new int[w.length];
    int sum = 0;
    for (int i = 0; i < w.length; i++) {
      sum += w[i];
      this.sums[i] = sum;
    }
    this.random = new Random();
  }

  public int pickIndex() {
    int num = random.nextInt(sums[sums.length - 1]) + 1;
    int index = Arrays.binarySearch(sums, num);
    if (index < 0) {
      index = -(index + 1);
    }
    return index;
  }

  public static void main(String[] args) {
    RandomPickWithWeight rp = new RandomPickWithWeight(new int[]{20, 5, 1});
    Map<Integer, Integer> counter = new HashMap<>();
    for (int i = 0; i < 100; i++) {
      int number = rp.pickIndex();
      counter.put(number, counter.getOrDefault(number, 0) + 1);
    }
    System.out.println(counter);
  }
}
