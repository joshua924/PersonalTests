package lc.sz1288;

public class FindMaxForm {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int t = 0; t < strs.length; t++) {
            int zeroCount = strs[t].length() - strs[t].replaceAll("0", "").length();
            int oneCount = strs[t].length() - zeroCount;
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (i + zeroCount <= m && j + oneCount <= n) {
                        dp[i + zeroCount][j + oneCount] = Math.max(dp[i][j] + 1, dp[i + zeroCount][j + oneCount]);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        FindMaxForm fm = new FindMaxForm();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        String[] strs1 = {"10", "0", "1"};
        System.out.println(fm.findMaxForm(strs, 5, 3));
        System.out.println(fm.findMaxForm(strs1, 1, 1));
    }
}
