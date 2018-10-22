package lc.sz1288;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * <p>
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 */
public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int k = i + 2;
            if (nums[i] == 0) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                while (k < nums.length && nums[k] < nums[i] + nums[j]) {
                    k++;
                }
                res += k - j - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TriangleNumber tn = new TriangleNumber();
        System.out.println(tn.triangleNumber(new int[]{0, 0}));
        System.out.println(tn.triangleNumber(new int[]{}));
        System.out.println(tn.triangleNumber(new int[]{2, 2, 3, 4}));
    }
}
