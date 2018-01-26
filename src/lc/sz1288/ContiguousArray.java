package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> index = new HashMap<>();
        int max = 0;
        int currentOnes = 0;
        index.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            currentOnes += (nums[i] == 1 ? 1 : -1);
            if (index.containsKey(currentOnes)) {
                max = Math.max(max, i - index.get(currentOnes));
            } else {
                index.put(currentOnes, i);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ContiguousArray ca = new ContiguousArray();
        int[] nums = {0, 1};
        System.out.println(ca.findMaxLength(nums));
    }
}
