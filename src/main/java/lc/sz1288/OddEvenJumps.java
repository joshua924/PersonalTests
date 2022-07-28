package lc.sz1288;

import java.util.Map;
import java.util.TreeMap;

/**
 * You are given an integer array arr. From some starting index, you can make a series of jumps.
 * The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps.
 * Note that the jumps are numbered, not the indices.
 *
 * You may jump forward from index i to index j (with i < j) in the following way:
 *
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value.
 * If there are multiple such indices j, you can only jump to the smallest such index j.
 *
 * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value.
 * If there are multiple such indices j, you can only jump to the smallest such index j.
 *
 * It may be the case that for some index i, there are no legal jumps.
 * A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by jumping some number of times (possibly 0 or more than once).
 *
 * Return the number of good starting indices.
 */
public class OddEvenJumps {
  public int oddEvenJumps(int[] arr) {
    boolean[] oddStart = new boolean[arr.length];
    boolean[] evenStart = new boolean[arr.length];
    TreeMap<Integer, Integer> valToIndex = new TreeMap<>();
    int result = 1;
    oddStart[arr.length - 1] = true;
    evenStart[arr.length - 1] = true;

    for (int i = arr.length - 1; i >= 0; i--) {
      Map.Entry<Integer, Integer> ceiling = valToIndex.ceilingEntry(arr[i]);
      if (ceiling != null && evenStart[ceiling.getValue()]) {
        oddStart[i] = true;
        result++;
      }
      Map.Entry<Integer, Integer> floor = valToIndex.floorEntry(arr[i]);
      if (floor != null && oddStart[floor.getValue()]) {
        evenStart[i] = true;
      }
      valToIndex.put(arr[i], i);
    }
    return result;
  }

  public static void main(String[] args) {
    OddEvenJumps solution = new OddEvenJumps();
    int[] arr = {10, 13, 12, 14, 15};
    System.out.println(solution.oddEvenJumps(arr));
    System.out.println(solution.oddEvenJumps(new int[]{2, 3, 1, 1, 4}));
  }
}
