package lc.sz1288;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 */
public class MaxSlidingWindow {
  public int[] maxSlidingWindow(int[] nums, int k) {
    TreeMap<Integer, Integer> count = new TreeMap<>();
    int index = 0;
    while (index < k) {
      count.put(nums[index], count.getOrDefault(nums[index], 0) + 1);
      index++;
    }
    int[] result = new int[nums.length - k + 1];
    result[0] = count.lastKey();
    while (index < nums.length) {
      int toAdd = nums[index];
      int toRemove = nums[index - k];
      count.put(toAdd, count.getOrDefault(toAdd, 0) + 1);
      int removeCount = count.get(toRemove);
      if (removeCount == 1) {
        count.remove(toRemove);
      } else {
        count.put(toRemove, removeCount - 1);
      }
      result[++index - k] = count.lastKey();
    }
    return result;
  }

  public static void main(String[] args) {
    MaxSlidingWindow solution = new MaxSlidingWindow();
    int[] result1 = solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    int[] result2 = solution.maxSlidingWindow(new int[]{3, 3, -1, -3}, 2);
    System.out.println(Arrays.toString(result1));
    System.out.println(Arrays.toString(result2));
  }
}
