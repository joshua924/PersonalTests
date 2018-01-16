package leetcode;

public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length + 1];
        for (int j = 0; j <= coins.length; ++j) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                dp[i][j] = dp[i][j - 1];
                if (coins[j - 1] <= i) {
                    dp[i][j] += dp[i - coins[j - 1]][j];
                }
            }
        }
        return dp[amount][coins.length];
    }

    public static void main(String[] args) {
        CoinChange2 cc = new CoinChange2();
        System.out.println(cc.change(5, new int[]{1, 2, 5}));
        System.out.println(cc.change(3, new int[]{2}));
        System.out.println(cc.change(10, new int[]{1, 2, 5, 10}));
    }
}
