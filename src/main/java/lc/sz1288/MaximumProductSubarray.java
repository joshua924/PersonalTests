package lc.sz1288;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 */
public class MaximumProductSubarray {
  public int maxProduct(int[] nums) {
    int[] max = new int[nums.length];
    int[] min = new int[nums.length];
    max[0] = nums[0];
    min[0] = nums[0];
    int result = nums[0];
    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];
      if (num >= 0) {
        max[i] = Integer.max(num, max[i - 1] * num);
        min[i] = min[i - 1] * num;
      } else {
        max[i] = min[i - 1] * num;
        min[i] = Integer.min(max[i - 1] * num, num);
      }
      result = Integer.max(result, max[i]);
    }
    return result;
  }

  public static void main(String[] args) {
    MaximumProductSubarray solution = new MaximumProductSubarray();
    System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
    System.out.println(solution.maxProduct(new int[]{-2}));
    System.out.println(solution.maxProduct(new int[]{-2, -3, 0, 7}));
  }
}
