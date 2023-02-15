package lc.sz1288;

/**
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 */
public class OneEditDistance {
  public boolean isOneEditDistance(String s, String t) {
    if (Math.abs(s.length() - t.length()) > 1 || s.equals(t)) {
      return false;
    }

    int[][] distances = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i <= s.length(); i++) {
      distances[i][0] = i;
    }
    for (int i = 0; i <= t.length(); i++) {
      distances[0][i] = i;
    }
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        distances[i][j] = Math.min(
            Math.min(distances[i - 1][j] + 1, distances[i][j - 1] + 1),
            distances[i - 1][j - 1] + (s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1)
        );
      }
    }

    return distances[s.length()][t.length()] == 1;
  }

  public static void main(String[] args) {
    OneEditDistance solution = new OneEditDistance();
    System.out.println(solution.isOneEditDistance("ab", "acb"));
    System.out.println(solution.isOneEditDistance("ab", "ab"));
    System.out.println(solution.isOneEditDistance("avvb", "av"));
    System.out.println(solution.isOneEditDistance("a", ""));
  }
}
