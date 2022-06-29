package lc.sz1288;

import java.util.Arrays;

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * <p>
 * Return the sum of the three integers.
 * <p>
 * You may assume that each input would have exactly one solution.
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int minDiff = Integer.MAX_VALUE;
        int minDiffValue = 0;
        Arrays.sort(nums);
        int first = 0;
        while (first < nums.length - 2) {
            int num1 = nums[first];
            int head = first + 1;
            int tail = nums.length - 1;
            while (head < tail) {
                int total = num1 + nums[head] + nums[tail];
                if (total < target) {
                    int diff = target - total;
                    if (diff < minDiff) {
                        minDiffValue = total;
                        minDiff = diff;
                    }
                    head++;
                } else if (total > target) {
                    int diff = total - target;
                    if (diff < minDiff) {
                        minDiffValue = total;
                        minDiff = diff;
                    }
                    tail--;
                } else {
                    return total;
                }
            }
            first++;
        }
        return minDiffValue;
    }

    public static void main(String[] args) {
        ThreeSumClosest ts = new ThreeSumClosest();
        int[] nums = {-1, 2, 1, -4};
        System.out.println(ts.threeSumClosest(nums, 1));
        System.out.println(ts.threeSumClosest(nums, -5));
    }
}
