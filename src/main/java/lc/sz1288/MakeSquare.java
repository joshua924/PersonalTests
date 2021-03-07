package lc.sz1288;

import java.util.stream.IntStream;

public class MakeSquare {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }
        int sum = IntStream.of(nums).sum();
        if (sum % 4 != 0) {
            return false;
        }
        return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sums, int index, int target) {
        if (index == nums.length) {
            if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (sums[i] + nums[index] <= target) {
                sums[i] += nums[index];
                if (dfs(nums, sums, index + 1, target)) {
                    return true;
                }
                sums[i] -= nums[index];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MakeSquare ms = new MakeSquare();
        int[] nums = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        System.out.println(ms.makesquare(nums));
    }
}
