package lc.sz1288;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 */
public class BasicCalculatorII {
  public int calculate(String s) {
    Deque<Integer> stack = new ArrayDeque<>();
    char operator = '+';
    int operand = 0;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (Character.isDigit(ch)) {
        operand = operand * 10 + (ch - '0');
      }
      if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
        if (operator == '+') {
          stack.push(operand);
        } else if (operator == '-') {
          stack.push(-operand);
        } else if (operator == '*') {
          stack.push(stack.pop() * operand);
        } else if (operator == '/') {
          stack.push(stack.pop() / operand);
        }
        operator = ch;
        operand = 0;
      }
    }

    int result = 0;
    while (!stack.isEmpty()) {
      result += stack.pop();
    }
    return result;
  }

  public static void main(String[] args) {
    BasicCalculatorII solution = new BasicCalculatorII();
    System.out.println(solution.calculate("3 + 2*  2"));
  }
}
