package lc.sz1288;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * <p>
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 * <p>
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class CountPalindromicSubstring {
    public int countSubstrings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += expand(s, i, i);
            res += expand(s, i, i + 1);
        }
        return res;
    }

    private int expand(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        CountPalindromicSubstring cps = new CountPalindromicSubstring();
        System.out.println(cps.countSubstrings("abc"));
        System.out.println(cps.countSubstrings("aaa"));
    }
}
