package lc.sz1288;

import java.util.*;

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
            int num1 = nums[first];
            int head = first + 1;
            int tail = nums.length - 1;
            while (head < tail) {
                int sum = num1 + nums[head] + nums[tail];
                if (sum < 0) {
                    head++;
                } else if (sum > 0) {
                    tail--;
                } else {
                    res.add(new ArrayList<>(Arrays.asList(num1, nums[head], nums[tail])));
                    head++;
                    tail--;
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
