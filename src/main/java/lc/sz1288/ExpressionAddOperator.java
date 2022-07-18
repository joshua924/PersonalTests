package lc.sz1288;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given a string num that contains only digits and an integer target,
 * return all possibilities to insert the binary operators '+', '-', and/or '*' between
 * the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 */
public class ExpressionAddOperator {
  public List<String> addOperators(String num, int target) {
    List<String> result = new ArrayList<>();
    dfs(num, 0, "", target, result);
    return result;
  }

  private void dfs(String num, int index, String currentExp, int target, List<String> result) {
    if (index == num.length()) {
      int currentVal = evaluate(currentExp);
      if (currentVal == target) {
        result.add(currentExp);
      }
      return;
    }
    // don't add any operator
    int digit = num.charAt(index) - '0';
    dfs(num, index + 1, currentExp + digit, target, result);
    if (!currentExp.isEmpty()) {
      dfs(num, index + 1, currentExp + "+" + digit, target, result);
      dfs(num, index + 1, currentExp + "-" + digit, target, result);
      dfs(num, index + 1, currentExp + "*" + digit, target, result);
    }
  }

  private int evaluate(String currentExp) {
    Deque<String> tokens = new ArrayDeque<>();
    int index = 0;
    while (index < currentExp.length()) {
      char ch = currentExp.charAt(index);
      if (ch == '+' || ch == '-' || ch == '*') {
        tokens.push(ch + "");
        index++;
      } else {
        int start = index;
        while (index < currentExp.length() && Character.isDigit(currentExp.charAt(index))) {
          index++;
        }
        String substring = currentExp.substring(start, index);
        int num = Integer.parseInt(substring);
        if (!tokens.isEmpty() && "*".equals(tokens.peek())) {
          tokens.pop();
          int another = Integer.parseInt(tokens.pop());
          tokens.push((num * another) + "");
        } else {
          tokens.push(substring);
        }
      }
    }

    int result = Integer.parseInt(tokens.pollLast());
    while (!tokens.isEmpty()) {
      String operator = tokens.pollLast();
      int num = Integer.parseInt(tokens.pollLast());
      if ("+".equals(operator)) {
        result += num;
      } else {
        result -= num;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    ExpressionAddOperator solution = new ExpressionAddOperator();
    System.out.println(solution.addOperators("123", 6));
    System.out.println(solution.addOperators("123", 15));
    System.out.println(solution.addOperators("123", 123));
  }
}
