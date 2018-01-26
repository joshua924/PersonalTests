package lc.sz1288;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        timePoints.sort(Comparator.naturalOrder());
        int size = timePoints.size();
        int minMinutes = Integer.MAX_VALUE;
        for (int i = 0; i < size - 1; i++) {
            String cur = timePoints.get(i);
            String next = timePoints.get(i + 1);
            int diff = getDiff(cur, next);
            minMinutes = Integer.min(minMinutes, diff);
        }
        String[] split = timePoints.get(0).split(":");
        String padded = (Integer.valueOf(split[0]) + 24) + ":" + split[1];
        minMinutes = Integer.min(minMinutes, getDiff(timePoints.get(timePoints.size() - 1), padded));
        return minMinutes;
    }

    private int getDiff(String cur, String next) {
        String[] a = cur.split(":");
        String[] b = next.split(":");
        return (Integer.valueOf(b[0]) - Integer.valueOf(a[0])) * 60 + Integer.valueOf(b[1]) - Integer.valueOf(a[1]);
    }

    public static void main(String[] args) {
        MinTimeDifference mtd = new MinTimeDifference();
        System.out.println(mtd.findMinDifference(Arrays.asList("01:39", "10:26", "21:43")));
    }
}
