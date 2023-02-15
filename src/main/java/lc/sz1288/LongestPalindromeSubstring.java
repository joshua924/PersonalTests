package lc.sz1288;

/**
 * Given a string s, return the longest palindromic substring in s.
 */
public class LongestPalindromeSubstring {
  public String longestPalindrome(String s) {
    char[] chars = s.toCharArray();
    boolean[][] isPalindrome = new boolean[chars.length][chars.length];

    isPalindrome[0][0] = true;
    for (int i = 1; i < chars.length; i++) {
        isPalindrome[i][i - 1] = true;
        isPalindrome[i][i] = true;
    }

    int max = 1;
    String substring = s.substring(0, 1);
    for (int i = 1; i < chars.length; i++) {
      for (int j = 0; j + i < chars.length; j++) {
        if (isPalindrome[j + 1][j + i - 1] && chars[j] == chars[j + i]) {
          isPalindrome[j][j + i] = true;
          if (i + 1 > max) {
            max = i + 1;
            substring = s.substring(j, j + i + 1);
          }
        }
      }
    }

    return substring;
  }

  public static void main(String[] args) {
    LongestPalindromeSubstring solution = new LongestPalindromeSubstring();
    System.out.println(solution.longestPalindrome("babad"));
    System.out.println(solution.longestPalindrome("babab"));
    System.out.println(solution.longestPalindrome("7"));
  }
}
