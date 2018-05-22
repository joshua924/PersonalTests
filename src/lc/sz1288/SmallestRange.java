package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmallestRange {
    public int[] smallestRange(List<List<Integer>> nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> integerList = nums.get(i);
            for (Integer num : integerList) {
                list.add(new int[]{num, i});
            }
        }
        list.sort(Comparator.comparingInt(arr -> arr[0]));
        Map<Integer, Integer> seen = new HashMap<>();
        int start = 0, end = 0;
        int[] res = new int[]{list.get(0)[0], list.get(list.size() - 1)[0]};
        while (end < list.size()) {
            System.out.println(start);
            int index = list.get(end)[1];
            seen.merge(index, 1, Integer::sum);
            if (seen.size() == nums.size()) {
                while (seen.get(list.get(start)[1]) > 1) {
                    seen.put(list.get(start)[1], seen.get(list.get(start)[1]) - 1);
                    start++;
                }
                int startVal = list.get(start)[0];
                int endVal = list.get(end)[0];
                if (endVal - startVal < res[1] - res[0] || (endVal - startVal == res[1] - res[0] && startVal < res[0])) {
                    res[0] = startVal;
                    res[1] = endVal;
                }
            }
            end++;
        }
        return res;
    }

    public static void main(String[] args) {
        SmallestRange sr = new SmallestRange();
        int[] res1 = sr.smallestRange(Arrays.asList(
                Arrays.asList(4, 10, 15, 24, 26),
                Arrays.asList(0, 9, 12, 20),
                Arrays.asList(5, 18, 22, 30)
        ));
        System.out.println(Arrays.toString(res1));
    }
}
