package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
public class ThreeSum {
  public List<List<Integer>> threeSum(int[] nums) {
    Set<List<Integer>> res = new HashSet<>();
    if (nums.length < 3) {
      return new ArrayList<>();
    }
    Arrays.sort(nums);
    int first = 0;
    while (first < nums.length - 2) {
      int i = first + 1;
      int j = nums.length - 1;
      while (i < j) {
        if (nums[first] + nums[i] + nums[j] < 0) {
          i++;
        } else if (nums[first] + nums[i] + nums[j] > 0) {
          j--;
        } else {
          res.add(Arrays.asList(nums[first], nums[i], nums[j]));
          i++;
          j--;
        }
      }
      first++;
    }
    return new ArrayList<>(res);
  }

  public static void main(String[] args) {
    ThreeSum ts = new ThreeSum();
    int[] nums = {-2, 0, 1, 1, 2};
    System.out.println(ts.threeSum(nums));
  }
}
