package lc.sz1288;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 */
public class TopKFrequentElements {
  /**
   * this approach has time complexity of O(nlogk), as adding elements into the PriorityQueue is
   * a logk operation.
   */
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> frequencies = new HashMap<>();
    for (int num : nums) {
      frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
    }
    PriorityQueue<Integer> topFrequencies = new PriorityQueue<>(k, Comparator.comparingInt(frequencies::get));
    frequencies
        .keySet()
        .forEach(key -> {
          topFrequencies.offer(key);
          if (topFrequencies.size() > k) {
            topFrequencies.poll();
          }
        });
    return topFrequencies
        .stream()
        .mapToInt(i -> i)
        .toArray();
  }

  public static void main(String[] args) {
    TopKFrequentElements solution = new TopKFrequentElements();
    System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3, 3, 3, 3, 4}, 2)));
  }
}
