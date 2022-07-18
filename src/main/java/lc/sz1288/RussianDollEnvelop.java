package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.
 *
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 *
 * Note: You cannot rotate an envelope.
 */
public class RussianDollEnvelop {
  public int maxEnvelopes(int[][] envelopes) {
    Arrays.sort(envelopes,
        (arr1, arr2) -> arr1[0] == arr2[0] ? arr2[1] - arr1[1] : arr1[0] - arr2[0]);
    int[] heights = new int[envelopes.length];
    for (int i = 0; i < envelopes.length; i++) {
      heights[i] = envelopes[i][1];
    }
    return lengthOfLIS(heights);
  }

  private int lengthOfLIS(int[] nums) {
    List<Integer> sequence = new ArrayList<>();
    for (int num : nums) {
      int index = Collections.binarySearch(sequence, num);
      index = index < 0 ? -index - 1 : index;
      if (index == sequence.size()) {
        sequence.add(num);
      } else {
        sequence.set(index, num);
      }
    }
    return sequence.size();
  }

  public static void main(String[] args) {
    RussianDollEnvelop solution = new RussianDollEnvelop();
    int[][] envelopes = {{46, 89}, {50, 53}, {52, 68}, {72, 45}, {77, 81}};
    System.out.println(solution.maxEnvelopes(envelopes));
    System.out.println(solution.maxEnvelopes(new int[][]{{1, 1}, {1, 1}}));
  }
}
