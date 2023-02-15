package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj].
 * Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 */
public class IntervalListIntersection {
  public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    int j = 0;
    while (i < firstList.length && j < secondList.length) {
      int[] i1 = firstList[i];
      int[] i2 = secondList[j];
      if (i1[0] > i2[1]) {
        j++;
      } else if (i1[1] < i2[0]) {
        i++;
      } else {
        int[] interval = new int[2];
        interval[0] = Integer.max(i1[0], i2[0]);
        interval[1] = Integer.min(i1[1], i2[1]);
        result.add(interval);
        if (i1[1] < i2[1]) {
          i++;
        } else {
          j++;
        }
      }
    }
    return result.toArray(new int[0][]);
  }

  public static void main(String[] args) {
    IntervalListIntersection solution = new IntervalListIntersection();
    int[][] l1 = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
    int[][] l2 = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
    System.out.println(Arrays.deepToString(solution.intervalIntersection(l1, l2)));
  }
}
