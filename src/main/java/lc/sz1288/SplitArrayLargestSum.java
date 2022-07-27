package lc.sz1288;

/**
 * Given an array nums which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 *
 * Write an algorithm to minimize the largest sum among these m subarrays.
 */
public class SplitArrayLargestSum {
  public int splitArray(int[] nums, int m) {
    int[] dp = new int[nums.length];
    int[] sums = new int[nums.length];

    int sum = 0;
    for (int i = nums.length - 1; i >= 0; i--) {
      sum += nums[i];
      dp[i] = sum;
      sums[i] = sum;
    }
    for (int i = 1; i < m; i++) {
      for (int left = 0; left < nums.length - 1; left++) {
        int min = Integer.MAX_VALUE;
        for (int split = left; split < nums.length - 1; split++) {
          int leftSum = sums[left] - sums[split + 1];
          min = Integer.min(min, Integer.max(leftSum, dp[split + 1]));
        }
        dp[left] = min;
      }
    }
    return dp[0];
  }

  public static void main(String[] args) {
    SplitArrayLargestSum solution = new SplitArrayLargestSum();
    int[] nums = {7, 2, 5, 10, 8};
    System.out.println(solution.splitArray(nums, 1));
    System.out.println(solution.splitArray(nums, 2));
    System.out.println(solution.splitArray(nums, 3));
    System.out.println(solution.splitArray(nums, 4));
    System.out.println(solution.splitArray(nums, 5));
  }
}
