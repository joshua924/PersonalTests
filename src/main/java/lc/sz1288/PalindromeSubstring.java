package lc.sz1288;

public class PalindromeSubstring {
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
            if (i < len - 1) {
                isPalindrome[i + 1][i] = s.charAt(i + 1) == s.charAt(i);
            }
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j + i < len; j++) {
                isPalindrome[j][j + i] = isPalindrome[j + 1][j + i - 1] && s.charAt(j) == s.charAt(j + i);
            }
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (isPalindrome[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public String longestSubstrings(String s) {
        if (s.isEmpty()) {
            return "";
        }
        int len = s.length();
        boolean[][] isPalindrome = new boolean[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
            if (i < len - 1) {
                isPalindrome[i + 1][i] = s.charAt(i + 1) == s.charAt(i);
            }
        }
        String longest = s.substring(0, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j + i < len; j++) {
                boolean palindrome = isPalindrome[j + 1][j + i - 1] && s.charAt(j) == s.charAt(j + i);
                if (palindrome) {
                    isPalindrome[j][j + i] = true;
                    if (longest.length() < i + 1) {
                        longest = s.substring(j, j + i + 1);
                    }
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        PalindromeSubstring ps = new PalindromeSubstring();
        System.out.println(ps.countSubstrings("aaa"));
        System.out.println(ps.countSubstrings("abc"));
        System.out.println(ps.countSubstrings("hewiudfygsduyirfteyu"));
        System.out.println(ps.longestSubstrings("aaa"));
        System.out.println(ps.longestSubstrings("abc"));
        System.out.println(ps.longestSubstrings("hewiudfygsduyirfteyu"));
    }
}
