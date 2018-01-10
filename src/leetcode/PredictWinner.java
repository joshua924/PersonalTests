package leetcode;

public class PredictWinner {
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        dp[0][0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j + i < nums.length; j++) {
                int pickLeft = nums[j] - dp[j + 1][j + i];
                int pickRight = nums[j + i] - dp[j][j + i - 1];
                dp[j][j + i] = Math.max(pickLeft, pickRight);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }

    public static void main(String[] args) {
        PredictWinner pw = new PredictWinner();
        int[] nums1 = {1, 5, 2};
        System.out.println(pw.PredictTheWinner(nums1));
        int[] nums2 = {1, 5, 233, 7};
        System.out.println(pw.PredictTheWinner(nums2));
    }
}
