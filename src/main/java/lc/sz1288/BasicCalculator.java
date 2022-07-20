package lc.sz1288;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 */
public class BasicCalculator {
  public int calculate(String s) {
    Deque<Integer> stack = new LinkedList<>();
    int sign = 1;
    int result = 0;
    int current = 0;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (Character.isDigit(ch)) {
        current = current * 10 + (ch - '0');
      } else if (ch == '(') {
        stack.push(result);
        stack.push(sign);
        sign = 1;
        result = 0;
      } else if (ch == ')') {
        result += current * sign;
        result *= stack.pop();
        result += stack.pop();
        current = 0;
      } else if (ch == '+') {
        result += current * sign;
        sign = 1;
        current = 0;
      } else if (ch == '-') {
        result += current * sign;
        sign = -1;
        current = 0;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    BasicCalculator bc = new BasicCalculator();
    System.out.println(bc.calculate("2-(1 + 2) "));
    System.out.println(bc.calculate(" ( 1+(4+5+2)-3)+(6+8)"));
  }
}
