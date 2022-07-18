package lc.sz1288;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * Example:
 * <p>
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {
  public int trap(int[] height) {
    int[] rightMax = new int[height.length];
    for (int i = height.length - 1, max = 0; i >= 0; i--) {
      max = Math.max(max, height[i]);
      rightMax[i] = max;
    }
    int rain = 0;
    int leftMax = 0;
    for (int i = 0; i < height.length - 1; i++) {
      int store = Math.min(leftMax, rightMax[i + 1]) - height[i];
      rain += Math.max(store, 0);
      leftMax = Math.max(leftMax, height[i]);
    }
    return rain;
  }

  public static void main(String[] args) {
    TrappingRainWater trw = new TrappingRainWater();
    int[] rains1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    int[] rains2 = {4, 2, 0, 3, 2, 5};
    System.out.println(trw.trap(rains1));
    System.out.println(trw.trap(rains2));
  }
}
