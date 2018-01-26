package lc.sz1288;

public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        int[][] res = new int[len][len];
        for (int i = 0; i < len; i++) {
            res[i][i] = 1;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j + i < len; j++) {
                res[j][j + i] = Math.max(res[j + 1][j + i], res[j][j + i - 1]);
                if (s.charAt(j) == s.charAt(j + i)) {
                    res[j][j + i] = Math.max(res[j][j + i], res[j + 1][j + i - 1] + 2);
                }
            }
        }
        return res[0][len - 1];
    }

    public static void main(String[] args) {
        LongestPalindromeSubseq lps = new LongestPalindromeSubseq();
        System.out.println(lps.longestPalindromeSubseq("ab"));
        System.out.println(lps.longestPalindromeSubseq("a"));
        System.out.println(lps.longestPalindromeSubseq(""));
        System.out.println(lps.longestPalindromeSubseq("uasghfsdigfuskfausdfsgfusvcjhxvmnbzewireiw"));
    }
}
