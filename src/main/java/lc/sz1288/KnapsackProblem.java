package lc.sz1288;

public class KnapsackProblem {
  public int maximumValue(int[] weights, int[] values, int capacity) {
    int[] dp = new int[capacity + 1];
    for (int i = 1; i <= values.length; i++) {
      int weight = weights[i - 1];
      int value = values[i - 1];
      for (int w = capacity; w >= weight; w--) {
        dp[w] = Math.max(dp[w], dp[w - weight] + value);
      }
    }
    return dp[capacity];
  }

  public static void main(String[] args) {
    KnapsackProblem kp = new KnapsackProblem();
    int[] weights = new int[]{2, 4, 6, 9};
    int[] values = new int[]{3, 5, 7, 10};
    System.out.println(kp.maximumValue(weights, values, 10));
    System.out.println(kp.maximumValue(weights, values, 11));
  }
}
