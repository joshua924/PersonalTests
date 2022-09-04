package lc.sz1288;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 */
public class PalindromePartition {
  public List<List<String>> partition(String s) {
    int n = s.length();
    boolean[][] isPalindrome = new boolean[n][n];

    isPalindrome[0][0] = true;
    for (int i = 1; i < n; i++) {
      isPalindrome[i][i] = true;
      isPalindrome[i][i - 1] = true;
    }
    for (int len = 1; len < n; len++) {
      for (int i = 0; i + len < n; i++) {
        if (isPalindrome[i + 1][i + len - 1] && s.charAt(i) == s.charAt(i + len)) {
          isPalindrome[i][i + len] = true;
        }
      }
    }

    List<List<String>> result = new LinkedList<>();
    backtrack(s, isPalindrome, 0, new LinkedList<>(), result);
    return result;
  }

  private void backtrack(String s, boolean[][] isPalindrome, int index, LinkedList<String> current,
                         List<List<String>> result) {
    if (index == s.length()) {
      result.add(new ArrayList<>(current));
      return;
    }
    for (int i = index; i < s.length(); i++) {
      if (isPalindrome[index][i]) {
        current.add(s.substring(index, i + 1));
        backtrack(s, isPalindrome, i + 1, current, result);
        current.removeLast();
      }
    }
  }

  public static void main(String[] args) {
    PalindromePartition solution = new PalindromePartition();
    System.out.println(solution.partition("aabaa"));
  }
}
