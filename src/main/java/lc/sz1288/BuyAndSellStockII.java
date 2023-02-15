package lc.sz1288;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 */
public class BuyAndSellStockII {
  public int maxProfit(int[] prices) {
    int[][] dp = new int[2][prices.length];
    dp[0][0] = 0;
    dp[1][0] = -prices[0];
    for (int i = 1; i < prices.length; i++) {
      dp[0][i] = Integer.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);
      dp[1][i] = Integer.max(dp[1][i - 1], dp[0][i - 1] - prices[i]);
    }
    return Integer.max(dp[0][prices.length - 1], 0);
  }

  public static void main(String[] args) {
    BuyAndSellStockII solution = new BuyAndSellStockII();
    int[] prices = {7, 1, 5, 3, 6, 4};
    System.out.println(solution.maxProfit(prices));
  }
}
