package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class CheckSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> sum = new HashMap<>();
        sum.put(0, -1);
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            currentSum += num;
            if (k != 0) {
                currentSum %= k;
            }
            if (sum.containsKey(currentSum)) {
                if (i - sum.get(currentSum) >= 2) {
                    return true;
                }
            } else {
                sum.put(currentSum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckSubarraySum css = new CheckSubarraySum();
        int[] nums1 = {2, 4, 7, 8};
        System.out.println(css.checkSubarraySum(nums1, -6));
        int[] nums2 = {2, 4, 728356425};
        System.out.println(css.checkSubarraySum(nums2, 6));
    }
}
