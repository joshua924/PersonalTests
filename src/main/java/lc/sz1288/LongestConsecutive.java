package lc.sz1288;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 */
public class LongestConsecutive {
  public int longestConsecutive(int[] nums) {
    Set<Integer> elements = new HashSet<>();
    for (int num : nums) {
      elements.add(num);
    }
    int longest = 0;
    for (int num : elements) {
      if (!elements.contains(num - 1)) {
        int length = 1;
        int val = num + 1;
        while (elements.contains(val)) {
          val++;
          length++;
        }
        longest = Integer.max(longest, length);
      }
    }
    return longest;
  }

  public static void main(String[] args) {
    LongestConsecutive solution = new LongestConsecutive();
    int[] nums1 = {100, 4, 200, 1, 3, 2};
    System.out.println(solution.longestConsecutive(nums1));
    int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
    System.out.println(solution.longestConsecutive(nums2));
  }
}
