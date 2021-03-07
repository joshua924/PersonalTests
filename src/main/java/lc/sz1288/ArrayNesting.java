package lc.sz1288;

import java.util.HashSet;
import java.util.Set;

public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = i;
            int len = 0;
            while (!set.contains(cur)) {
                set.add(cur);
                cur = nums[cur];
                len++;
            }
            maxLength = Integer.max(maxLength, len);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        ArrayNesting an = new ArrayNesting();
        int[] nums = {5, 4, 0, 3, 1, 6, 2};
        System.out.println(an.arrayNesting(nums));
    }
}
