package lc.sz1288;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 */
public class BasicCalculator {
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int rs = 0;
        int sign = 1;
        stack.push(1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                System.out.println(stack);
                stack.push(stack.peekFirst() * sign);
                System.out.println(stack);
                sign = 1;
            } else if (s.charAt(i) == ')') {
                stack.pop();
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) != ' ') {
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(++i) - '0';
                }
                rs += sign * stack.peekFirst() * num;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        System.out.println(bc.calculate("2-(1 + 2) "));
//        System.out.println(bc.calculate(" ( 1+(4+5+2)-3)+(6+8)"));
    }
}
