package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sums = new HashMap<>();
        int sum = 0;
        int countOfK = 0;
        sums.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            countOfK += sums.getOrDefault(sum - k, 0);
            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
        }
        return countOfK;
    }

    public static void main(String[] args) {
        SubarraySum ss = new SubarraySum();
        int[] nums = {1, 1, 1, 1};
        System.out.println(ss.subarraySum(nums, 1));
        System.out.println(ss.subarraySum(nums, 2));
        System.out.println(ss.subarraySum(nums, 4));
        nums = new int[]{2, 45, 3, 47, -47, 1, 1, 1};
        System.out.println(ss.subarraySum(nums, 50));
    }
}
