package lc.sz1288;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed 105
 */
public class DecodeString {
  public String decodeString(String s) {
    int count = 0;
    Stack<String> stack = new Stack<>();
    String current = "";

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (Character.isDigit(ch)) {
        count = count * 10 + (ch - '0');
      } else if (Character.isAlphabetic(ch)) {
        current = current + ch;
      } else if (ch == '[') {
        stack.push(current);
        stack.push(count + "");
        count = 0;
        current = "";
      }
      if (ch == ']' || i == s.length() - 1) {
        int times = 1;
        String res = "";
        if (!stack.isEmpty()) {
          times = Integer.parseInt(stack.pop());
          res = stack.pop();
        }
        for (int t = 0; t < times; t++) {
          res += current;
        }
        current = res;
      }
    }
    return current;
  }

  public static void main(String[] args) {
    DecodeString solution = new DecodeString();
    System.out.println(solution.decodeString("a3[b]"));
    System.out.println(solution.decodeString("a"));
    System.out.println(solution.decodeString("7[z]"));
    System.out.println(solution.decodeString("3[a]2[bc]"));
    System.out.println(solution.decodeString("3[a2[c]]"));
  }
}
