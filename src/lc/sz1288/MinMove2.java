package lc.sz1288;

import java.util.Arrays;

public class MinMove2 {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int moves = 0;
        for (int each : nums) {
            moves += Math.abs(median - each);
        }
        return moves;
    }

    public static void main(String[] args) {
        MinMove2 mw = new MinMove2();
        int[] nums1 = {2, 3, 4};
        int[] nums2 = {4, 3, 2};
        int[] nums3 = {1, 97, 98, 99, 100};
        System.out.println(mw.minMoves2(nums1));
        System.out.println(mw.minMoves2(nums2));
        System.out.println(mw.minMoves2(nums3));
    }
}
