package lc.sz1288;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given a 0-indexed string s that you must perform k replacement operations on.
 * The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other.
 * The testcases will be generated such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 */
public class FindReplaceInString {
  public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
    StringBuilder result = new StringBuilder(s);
    int i = 0;
    int n = indices.length;
    List<Integer> order = IntStream
        .range(0, n)
        .boxed()
        .sorted(Comparator.comparingInt(a -> indices[a]))
        .collect(Collectors.toList());
    int diff = 0;
    while (i < n) {
      int index = order.get(i);
      int stringIndex = indices[index];
      if (s
          .substring(stringIndex)
          .startsWith(sources[index])) {
        int builderIndex = stringIndex + diff;
        result.replace(builderIndex, builderIndex + sources[index].length(), targets[index]);
        diff += targets[index].length() - sources[index].length();
      }
      i++;
    }
    return result.toString();
  }

  public static void main(String[] args) {
    FindReplaceInString solution = new FindReplaceInString();
    int[] indices = {3, 5, 1};
    String[] sources = {"kg", "ggq", "mo"};
    String[] targets = {"s", "so", "bfr"};
    System.out.println(solution.findReplaceString("vmokgggqzp", indices, sources, targets));
  }
}
