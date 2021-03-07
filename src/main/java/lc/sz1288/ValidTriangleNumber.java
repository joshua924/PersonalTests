package lc.sz1288;

import java.util.Arrays;

public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        int head = 0, tail = len - 1;
        while (head < tail - 1) {
            int lower = head;
            int upper = tail - 1;
            while (lower < upper) {
                if (nums[lower] + nums[upper] <= nums[tail]) {
                    lower++;
                } else {
                    count += upper - lower;
                    upper--;
                }
            }
            tail--;
        }
        return count;
    }

    public static void main(String[] args) {
        ValidTriangleNumber vtn = new ValidTriangleNumber();
        int[] nums = {2, 2, 3, 4};
        System.out.println(vtn.triangleNumber(nums));
    }
}
