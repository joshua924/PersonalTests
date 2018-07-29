package lc.sz1288;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsecutiveSubsequences {
    public boolean isPossible(int[] nums) {
        Map<Integer, Long> map = IntStream.of(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Integer, Integer> pendingSequences = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) == 0) {
                continue;
            }
            if (pendingSequences.getOrDefault(num, 0) > 0) {
                pendingSequences.put(num, pendingSequences.get(num) - 1);
                pendingSequences.merge(num + 1, 1, (existing, newVal) -> existing + newVal);
            } else if (map.getOrDefault(num + 1, 0L) > 0 && map.getOrDefault(num + 2, 0L) > 0) {
                map.put(num + 1, map.get(num + 1) - 1);
                map.put(num + 2, map.get(num + 2) - 1);
                pendingSequences.merge(num + 3, 1, (existing, newVal) -> existing + newVal);
            } else {
                return false;
            }
            map.put(num, map.get(num) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        ConsecutiveSubsequences cs = new ConsecutiveSubsequences();
        int[] nums = {1, 2, 3, 4, 4, 5};
        System.out.println(cs.isPossible(nums));
    }
}
