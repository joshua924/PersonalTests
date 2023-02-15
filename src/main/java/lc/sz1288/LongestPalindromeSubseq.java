package lc.sz1288;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by
 * deleting some or no elements without changing the order of the remaining elements.
 */
public class LongestPalindromeSubseq {
  public int longestPalindromeSubseq(String s) {
    int len = s.length();
    if (len == 0) {
      return 0;
    }
    int[][] res = new int[len][len];
    for (int i = 0; i < len; i++) {
      res[i][i] = 1;
    }
    for (int i = 1; i < len; i++) {
      for (int start = 0; start + i < len; start++) {
        res[start][start + i] = Math.max(res[start + 1][start + i], res[start][start + i - 1]);
        if (s.charAt(start) == s.charAt(start + i)) {
          res[start][start + i] = Math.max(res[start][start + i], res[start + 1][start + i - 1] + 2);
        }
      }
    }
    return res[0][len - 1];
  }

  public static void main(String[] args) {
    LongestPalindromeSubseq lps = new LongestPalindromeSubseq();
    System.out.println(lps.longestPalindromeSubseq("ab"));
    System.out.println(lps.longestPalindromeSubseq("a"));
    System.out.println(lps.longestPalindromeSubseq(""));
    System.out.println(lps.longestPalindromeSubseq("uasghfsdigfuskfausdfsgfusvcjhxvmnbzewireiw"));
  }
}
