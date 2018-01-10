package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TargetSumWay {
    public int findTargetSumWays(int[] nums, int S) {
        List<Integer> sums = findTargetSumWays(nums, 0, nums.length - 1);
        return (int) sums.stream().filter(a -> a == S).count();
    }

    private List<Integer> findTargetSumWays(int[] nums, int start, int end) {
        if (start == end) {
            return Arrays.asList(nums[start], -nums[start]);
        }
        int mid = start + (end - start) / 2;
        List<Integer> res = new ArrayList<>();
        List<Integer> rightSums = findTargetSumWays(nums, mid + 1, end);
        for (int left : findTargetSumWays(nums, start, mid)) {
            for (int right : rightSums) {
                res.add(left + right);
            }
        }
        return res;
    }
}
