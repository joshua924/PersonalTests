package lc.sz1288;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rank {
    private static final String[] RANK_NAMES = {"Gold Medal", "Silver Medal", "Bronze Medal"};

    public String[] findRelativeRanks(int[] nums) {
        int length = nums.length;
        int[] copy = new int[length];
        String[] res = new String[length];
        System.arraycopy(nums, 0, copy, 0, length);
        Arrays.sort(copy);
        Map<Integer, String> map = IntStream.range(1, length + 1).boxed().collect(Collectors.toMap(i -> copy[length - i], this::getRankName));
        for (int i = 0; i < length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }

    private String getRankName(int rank) {
        return rank <= 3 ? RANK_NAMES[rank - 1] : String.valueOf(rank);
    }

    public static void main(String[] args) {
        Rank rank = new Rank();
        int[] nums = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(rank.findRelativeRanks(nums)));
    }
}
