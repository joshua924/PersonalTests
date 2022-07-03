package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParenthesis {
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    if (n == 0) {
      return res;
    }
    backtrack(res, "", n, n);
    return res;
  }

  private void backtrack(List<String> res, String current, int left, int right) {
    if (left == 0 && right == 0) {
      res.add(current);
      return;
    }
    if (left > 0) {
      backtrack(res, current + "(", left - 1, right);
    }
    if (right > left) {
      backtrack(res, current + ")", left, right - 1);
    }
  }

  public static void main(String[] args) {
    GenerateParenthesis solution = new GenerateParenthesis();
    System.out.println(solution.generateParenthesis(4));
  }
}
