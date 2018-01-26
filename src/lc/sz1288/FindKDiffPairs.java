package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class FindKDiffPairs {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        int pair = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (k == 0) {
                if (map.containsKey(num)) {
                    if (map.get(num) == 0) {
                        pair++;
                        map.put(num, 1);
                    }
                } else {
                    map.put(num, 0);
                }
            } else {
                if (map.containsKey(num)) {
                    continue;
                }
                if (map.containsKey(num + k)) {
                    if (map.get(num + k) == 0) {
                        pair++;
                        map.put(num + k, 1);
                    } else if (map.get(num + k) == 2) {
                        pair++;
                        map.put(num + k, 3);
                    }
                }
                if (map.containsKey(num - k) && (map.get(num - k) == 0 || map.get(num - k) == 1)) {
                    if (map.get(num - k) == 0) {
                        pair++;
                        map.put(num - k, 2);
                    } else if (map.get(num - k) == 1) {
                        pair++;
                        map.put(num - k, 3);
                    }
                }
                map.put(num, 0);
            }
        }
        return pair;
    }

    public static void main(String[] args) {
        FindKDiffPairs fkd = new FindKDiffPairs();
        int[] nums = {3, 1, 4, 1, 5};
        System.out.println(fkd.findPairs(nums, 2));
    }
}
