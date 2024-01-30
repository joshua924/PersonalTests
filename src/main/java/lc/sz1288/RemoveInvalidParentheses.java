package lc.sz1288;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 */
public class RemoveInvalidParentheses {
  public List<String> removeInvalidParentheses(String s) {
    int leftToRemove = 0;
    int rightToRemove = 0;
    for (char ch : s.toCharArray()) {
      if (ch == '(') {
        leftToRemove++;
      } else if (ch == ')') {
        if (leftToRemove == 0) {
          rightToRemove++;
        } else {
          leftToRemove--;
        }
      }
    }

    Set<String> result = new HashSet<>();
    backtrack(new StringBuilder(s), 0, leftToRemove, rightToRemove, 0, 0, result);
    return new ArrayList<>(result);
  }

  private void backtrack(StringBuilder builder, int index, int leftRemain, int rightRemain,
                         int leftCount, int rightCount, Set<String> result) {
    if (index == builder.length()) {
      if (leftRemain == 0 && rightRemain == 0) {
        result.add(builder.toString());
      }
      return;
    }
    char ch = builder.charAt(index);
    if (ch == '(') {
      backtrack(builder, index + 1, leftRemain, rightRemain, leftCount + 1, rightCount, result);
      if (leftRemain > 0) {
        builder.deleteCharAt(index);
        backtrack(builder, index, leftRemain - 1, rightRemain, leftCount, rightCount, result);
        builder.insert(index, ch);
      }
    } else if (ch == ')') {
      if (rightCount < leftCount) {
        backtrack(builder, index + 1, leftRemain, rightRemain, leftCount, rightCount + 1, result);
      }
      if (rightRemain > 0) {
        builder.deleteCharAt(index);
        backtrack(builder, index, leftRemain, rightRemain - 1, leftCount, rightCount, result);
        builder.insert(index, ch);
      }
    } else {
      backtrack(builder, index + 1, leftRemain, rightRemain, leftCount, rightCount, result);
    }
  }

  public static void main(String[] args) {
    RemoveInvalidParentheses solution = new RemoveInvalidParentheses();
    System.out.println(solution.removeInvalidParentheses("(a)())()"));
    System.out.println(solution.removeInvalidParentheses("()())(bb)"));
  }
}
