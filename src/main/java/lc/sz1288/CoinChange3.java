package lc.sz1288;

/**
 * You are given coins of different denominations and a total amount of money, but you only have certain amount of each coin.
 * Decide the minimum number of coins to make up the total amount.
 */
public class CoinChange3 {
    public int minCoins(int[] coins, int[] count, int amount) {
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        backtrack(res, coins, count, 0, 0, amount);
        return res[0] == Integer.MAX_VALUE ? -1 : res[0];
    }

    private void backtrack(int[] res, int[] coins, int[] remain, int current, int sum, int amount) {
        if (sum > amount) {
            return;
        }
        if (sum == amount) {
            res[0] = Math.min(res[0], current);
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            if (remain[i] > 0) {
                remain[i]--;
                backtrack(res, coins, remain, current + 1, sum + coins[i], amount);
                remain[i]++;
            }
        }
    }

    public static void main(String[] args) {
        CoinChange3 cc3 = new CoinChange3();
        System.out.println(cc3.minCoins(new int[0], new int[0], 1));
        System.out.println(cc3.minCoins(new int[0], new int[0], 0));
        int[] coins = {1, 5, 10, 25};
        int[] count1 = {100, 100, 100, 100};
        System.out.println(cc3.minCoins(coins, count1, 11));
        int[] count2 = {4, 1, 0, 1};
        System.out.println(cc3.minCoins(coins, count2, 11));
        int[] count3 = {8, 1, 0, 1};
        System.out.println(cc3.minCoins(coins, count3, 11));
    }
}
