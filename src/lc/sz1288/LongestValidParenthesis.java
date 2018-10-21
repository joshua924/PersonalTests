package lc.sz1288;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Example 1:
 * <p>
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 * <p>
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        // dp[i] represents the longest valid length ending at i
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i > 2 ? dp[i - 2] : 0);
                } else if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    dp[i] = 2 + dp[i - 1] + (i - 2 - dp[i - 1] >= 0 ? dp[i - 2 - dp[i - 1]] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParenthesis lvp = new LongestValidParenthesis();
        System.out.println(lvp.longestValidParentheses(""));
        System.out.println(lvp.longestValidParentheses("("));
        System.out.println(lvp.longestValidParentheses("(()"));
        System.out.println(lvp.longestValidParentheses("(()()"));
        System.out.println(lvp.longestValidParentheses("()()))"));
    }
}
