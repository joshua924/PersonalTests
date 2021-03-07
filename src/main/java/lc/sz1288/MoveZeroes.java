package lc.sz1288;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int writer = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[writer] = num;
                writer++;
            }
        }
        while (writer < nums.length) {
            nums[writer++] = 0;
        }
    }

    public static void main(String[] args) {
        MoveZeroes mz = new MoveZeroes();
        int[] nums = new int[]{0, 1, 0, 3, 12};
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1, 0, 1};
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{0, 0, 0};
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{7, 8, 9};
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{7, 8, 9, 0};
        mz.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
