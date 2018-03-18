package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class HarmoniousSubsequence {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        long max = 0;
        for (int num : map.keySet()) {
            if (map.containsKey(num + 1)) {
                max = Math.max(max, map.get(num) + map.get(num + 1));
            }
        }
        return (int) max;
    }

    public static void main(String[] args) {
        HarmoniousSubsequence hs = new HarmoniousSubsequence();
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println(hs.findLHS(nums));
        int[] nums1 = {1, 1, 1, 1};
        System.out.println(hs.findLHS(nums1));
    }
}
