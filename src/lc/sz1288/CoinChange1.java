package lc.sz1288;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 */
public class CoinChange1 {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        int[] minCoins = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            minCoins[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (coin <= i && minCoins[i - coin] != Integer.MAX_VALUE) {
                    minCoins[i] = Math.min(minCoins[i], minCoins[i - coin] + 1);
                }
            }
        }
        return minCoins[amount] == Integer.MAX_VALUE ? -1 : minCoins[amount];
    }
}
