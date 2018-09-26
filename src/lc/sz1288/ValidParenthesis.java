package lc.sz1288;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
 * <p>
 * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * 4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * 5. An empty string is also valid.
 */
public class ValidParenthesis {
    public boolean checkValidString(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {

        }
    }
}
