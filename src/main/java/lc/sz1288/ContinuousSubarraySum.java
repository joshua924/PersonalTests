package lc.sz1288;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray
 * of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k.
 * 0 is always a multiple of k.
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */
public class ContinuousSubarraySum {
  public boolean checkSubarraySum(int[] nums, int k) {
    int[] sumsModK = new int[nums.length];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      sumsModK[i] = sum % k;
    }
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < sumsModK.length; i++) {
      int current = sumsModK[i];
      if (current == 0 && i >= 1) {
        return true;
      }
      if (seen.containsKey(current)) {
        if (seen.get(current) < i - 1) {
          return true;
        }
      } else {
        seen.put(current, i);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    ContinuousSubarraySum solution = new ContinuousSubarraySum();
    int[] nums = {23, 2, 6, 4, 7};
    System.out.println(solution.checkSubarraySum(nums, 6));
    System.out.println(solution.checkSubarraySum(nums, 5));
    System.out.println(solution.checkSubarraySum(nums, 23));
    System.out.println(solution.checkSubarraySum(nums, 42));
    System.out.println(solution.checkSubarraySum(new int[]{5, 0, 0, 0}, 3));
  }
}
