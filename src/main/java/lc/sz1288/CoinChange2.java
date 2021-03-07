package lc.sz1288;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (coin <= i) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange2 cc = new CoinChange2();
        System.out.println(cc.change(5, new int[]{1, 2, 5}));
        System.out.println(cc.change(3, new int[]{2}));
        System.out.println(cc.change(10, new int[]{1, 2, 5, 10}));
    }
}
