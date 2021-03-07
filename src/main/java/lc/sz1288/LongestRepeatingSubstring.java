package lc.sz1288;

public class LongestRepeatingSubstring {
    private static final String NONE = "NONE";

    public String longestRepeatingSubstring(String s) {
        int n = s.length();
        int dp[][] = new int[n + 1][n + 1];
        int max = 0;
        int start = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1) && j - i > dp[i - 1][j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        start = i - max;
                    }
                }
            }
        }
        if (max == 0 || s.matches(" *")) {
            return NONE;
        }
        return s.substring(start, start + max);
    }

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) {
        LongestRepeatingSubstring lrs = new LongestRepeatingSubstring();
        System.out.println(lrs.longestRepeatingSubstring("banana"));
        System.out.println(lrs.longestRepeatingSubstring("abcde"));
        System.out.println(lrs.longestRepeatingSubstring("abcda"));
        System.out.println(lrs.longestRepeatingSubstring("    "));
        System.out.println(lrs.longestRepeatingSubstring("babababab"));
        System.out.println(lrs.longestRepeatingSubstring("ccccccc"));
    }
}
