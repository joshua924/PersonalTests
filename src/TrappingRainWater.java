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
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        for (int i = 0, max = 0; i < height.length; i++) {
            max = Math.max(max, height[i]);
            leftMax[i] = max;
        }
        for (int i = height.length - 1, max = 0; i >= 0; i--) {
            max = Math.max(max, height[i]);
            rightMax[i] = max;
        }
        int rain = 0;
        for (int i = 0; i < height.length; i++) {
            int add = Math.min(leftMax[i], rightMax[i]) - height[i];
            rain += add;
        }
        return rain;
    }

    public static void main(String[] args) {
        TrappingRainWater trw = new TrappingRainWater();
        int[] rains = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trw.trap(rains));
        System.out.println(trw.trap(new int[]{}));
        System.out.println(trw.trap(new int[]{1}));
        System.out.println(trw.trap(new int[]{1, 2}));
    }
}
