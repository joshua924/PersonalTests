package lc.sz1288;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 */
public class MaximumSubarray {
  public int maxSubArray(int[] nums) {
    int[] maxEndingAt = new int[nums.length];
    maxEndingAt[0] = nums[0];
    int max = nums[0];

    for (int i = 1; i < nums.length; i++) {
      maxEndingAt[i] = Math.max(nums[i], maxEndingAt[i - 1] + nums[i]);
      max = Math.max(max, maxEndingAt[i]);
    }

    return max;
  }

  public static void main(String[] args) {
    MaximumSubarray solution = new MaximumSubarray();
    System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    System.out.println(solution.maxSubArray(new int[]{-1}));
  }
}
