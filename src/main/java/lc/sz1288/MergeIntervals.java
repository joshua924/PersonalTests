package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
    int mergedStart = intervals[0][0];
    int mergedEnd = intervals[0][1];
    List<int[]> result = new ArrayList<>();

    for (int[] interval : intervals) {
      int start = interval[0];
      int end = interval[1];
      if (start > mergedEnd) {
        result.add(new int[]{mergedStart, mergedEnd});
        mergedStart = start;
        mergedEnd = end;
      } else {
        mergedEnd = Math.max(mergedEnd, end);
      }
    }
    result.add(new int[]{mergedStart, mergedEnd});

    return result.toArray(new int[0][]);
  }

  public static void main(String[] args) {
    MergeIntervals solution = new MergeIntervals();
    int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    System.out.println(Arrays.deepToString(solution.merge(intervals)));
    System.out.println(Arrays.deepToString(solution.merge(new int[][]{{1, 4}, {4, 5}})));
  }
}
