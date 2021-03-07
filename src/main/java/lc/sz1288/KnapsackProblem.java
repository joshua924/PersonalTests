package lc.sz1288;

import com.google.common.base.Preconditions;

public class KnapsackProblem {
    public int maximumValue(int[] weights, int[] values, int capacity) {
        Preconditions.checkArgument(weights.length == values.length && capacity > 0);
        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 0; i < capacity; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= values.length; i++) {
            int weight = weights[i - 1];
            int value = values[i - 1];
            for (int w = 0; w <= capacity; w++) {
                if (weight > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight] + value);
                }
            }
        }
        return dp[values.length][capacity];
    }

    public static void main(String[] args) {
        KnapsackProblem kp = new KnapsackProblem();
        int[] weights = new int[]{2, 4, 6, 9};
        int[] values = new int[]{3, 5, 7, 10};
        System.out.println(kp.maximumValue(weights, values, 10));
        System.out.println(kp.maximumValue(weights, values, 11));
    }
}
