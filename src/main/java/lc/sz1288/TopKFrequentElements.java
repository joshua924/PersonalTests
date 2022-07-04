package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
    PriorityQueue<Map.Entry<Integer, Integer>> topFrequencies = new PriorityQueue<>(k + 1,
        Comparator.comparingInt(Map.Entry::getValue));
    frequencies
        .entrySet()
        .forEach(entry -> {
          topFrequencies.offer(entry);
          if (topFrequencies.size() > k) {
            topFrequencies.poll();
          }
        });
    return topFrequencies
        .stream()
        .mapToInt(Map.Entry::getKey)
        .toArray();
  }

  /**
   * this approach uses a bucket array with index being the frequency, after populating the
   * bucket array, we simply do a reverse for loop to find top frequency elements until we have k
   * numbers. The time complexity for this approach should be O(n), not very space efficient if
   * the nums array is huge.
   */
  @SuppressWarnings("unchecked")
  public int[] topKFrequentBucket(int[] nums, int k) {
    Map<Integer, Integer> frequencies = new HashMap<>();
    for (int num : nums) {
      frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
    }
    List<Integer>[] buckets = new List[nums.length];
    frequencies.forEach((num, freq) -> {
      List<Integer> list = buckets[freq - 1];
      if (list == null) {
        list = new ArrayList<>();
      }
      list.add(num);
      buckets[freq] = list;
    });

    List<Integer> result = new ArrayList<>();
    for (int i = nums.length - 1; i >= 0; i--) {
      if (result.size() >= k) {
        break;
      }
      if (buckets[i] != null) {
        result.addAll(buckets[i]);
      }
    }
    return result
        .stream()
        .mapToInt(i -> i)
        .toArray();
  }

  public static void main(String[] args) {
    TopKFrequentElements solution = new TopKFrequentElements();
    System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3, 3, 3, 3, 4}, 2)));
    System.out.println(Arrays.toString(solution.topKFrequentBucket(new int[]{1, 1, 1, 2, 2, 3, 3, 3, 4}, 2)));
  }
}
