package lc.sz1288;

/**
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
 * Example:
 * Input:
 * [[0,1,1,0],
 * [0,1,1,0],
 * [0,0,0,1]]
 * Output: 3
 * Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineInMatrix {
    public int longestLine(int[][] M) {
        int m = M.length;
        if (m == 0) {
            return 0;
        }
        int n = M[0].length;
        int[][][] dp = new int[m + 2][n + 2][4];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (M[i - 1][j - 1] == 1) {
                    dp[i][j][0] = dp[i][j - 1][0] + 1;
                    dp[i][j][1] = dp[i - 1][j - 1][1] + 1;
                    max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
                }
            }
            for (int j = n; j >= 1; j--) {
                if (M[i - 1][j - 1] == 1) {
                    dp[i][j][2] = dp[i - 1][j][2] + 1;
                    dp[i][j][3] = dp[i - 1][j + 1][3] + 1;
                    max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestLineInMatrix llim = new LongestLineInMatrix();
        int[][] matrix = {{1, 1, 1, 1}, {0, 1, 1, 0}, {0, 0, 0, 1}};
        System.out.println(llim.longestLine(matrix));
    }
}
