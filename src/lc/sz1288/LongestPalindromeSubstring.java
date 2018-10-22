package lc.sz1288;

public class LongestPalindromeSubstring {
    public String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int max = Math.max(goFromCenter(s, i, i), goFromCenter(s, i, i + 1));
            if (max > end - start) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end);
    }

    private int goFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left + 1;
    }
}
