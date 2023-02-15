package lc.sz1288;

/**
 * 1. Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 * 2. Given a binary array nums, return the maximum number of consecutive 1's in the array if you
 * can flip at most one 0.
 *
 * 3. Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array
 * if you can flip at most k 0's.
 */
public class MaxConsecutiveOnes {
  public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0;
    int current = 0;
    for (int num : nums) {
      if (num == 0) {
        current = 0;
      } else {
        max = Integer.max(max, ++current);
      }
    }
    return max;
  }

  public int findMaxConsecutiveOnesFlipOne(int[] nums) {
    int zeroCount = 0;
    int max = 1;
    int head = 0;
    int tail = 0;
    while (tail < nums.length) {
      if (nums[tail] == 0) {
        zeroCount++;
        while (zeroCount > 1) {
          while (nums[head] != 0) {
            head++;
          }
          zeroCount--;
          head++;
        }
      }
      max = Integer.max(max, tail - head + 1);
      tail++;
    }
    return max;
  }

  public int longestOnes(int[] nums, int k) {
    int zeroCount = 0;
    int max = 0;
    int head = 0;
    int tail = 0;
    while (tail < nums.length) {
      if (nums[tail] == 0) {
        zeroCount++;
        while (zeroCount > k) {
          while (nums[head] != 0) {
            head++;
          }
          zeroCount--;
          head++;
        }
      }
      max = Integer.max(max, tail - head + 1);
      tail++;
    }
    return max;
  }

  public static void main(String[] args) {
    MaxConsecutiveOnes solution = new MaxConsecutiveOnes();
    int[] nums = {1, 0, 1, 1, 0, 1, 1, 1};
    System.out.println(solution.findMaxConsecutiveOnes(nums));
    System.out.println(solution.findMaxConsecutiveOnesFlipOne(nums));
    System.out.println(solution.longestOnes(nums, 2));
    System.out.println(solution.longestOnes(new int[]{0, 0, 0}, 2));
    System.out.println(solution.longestOnes(new int[]{0, 0, 0}, 0));
  }
}
