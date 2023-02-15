package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 */
public class MinimumRemoveToValid {
  public String minRemoveToMakeValid(String s) {
    List<Integer> indexToRemove = new ArrayList<>();
    int leftParams = 0;

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(') {
        leftParams++;
      } else if (ch == ')') {
        if (leftParams > 0) {
          leftParams--;
        } else {
          indexToRemove.add(i);
        }
      }
    }
    Collections.reverse(indexToRemove);
    StringBuilder sb = new StringBuilder(s);
    for (int index : indexToRemove) {
      sb.deleteCharAt(index);
    }
    while (leftParams-- > 0) {
      sb.deleteCharAt(sb.lastIndexOf("("));
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    MinimumRemoveToValid solution = new MinimumRemoveToValid();
    System.out.println(solution.minRemoveToMakeValid("())()((("));
    System.out.println(solution.minRemoveToMakeValid("a)b(c)d"));
    System.out.println(solution.minRemoveToMakeValid("))(("));
  }
}
