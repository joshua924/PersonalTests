package lc.sz1288;

/**
 * So the area of water that can be stored from a to b is determined by:
 * (b - a) * min(height[a], height[b])
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;
        int max = 0;
        while (tail > head) {
            max = Math.max(max, getArea(height, head, tail));
            if (height[tail] > height[head]) {
                head++;
            } else {
                tail--;
            }
        }
        return max;
    }

    private int getArea(int[] height, int head, int tail) {
        return (tail - head) * Math.min(height[tail], height[head]);
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(solution.maxArea(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(solution.maxArea(new int[]{9, 1, 9}));
        System.out.println(solution.maxArea(new int[]{}));
        System.out.println(solution.maxArea(new int[]{1, 1}));
        System.out.println(solution.maxArea(new int[]{2, 3, 10, 5, 7, 8, 9}));
    }
}
