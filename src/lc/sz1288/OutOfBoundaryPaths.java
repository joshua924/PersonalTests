package lc.sz1288;

public class OutOfBoundaryPaths {
    private static final int MOD = 1000000007;

    public int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0) {
            return 0;
        }
        int[][][] ways = new int[N][m][n];
        for (int p = 0; p < m; p++) {
            ways[0][p][0]++;
            ways[0][p][n - 1]++;
        }
        for (int q = 0; q < n; q++) {
            ways[0][0][q]++;
            ways[0][m - 1][q]++;
        }
        for (int step = 1; step < N; step++) {
            for (int p = 0; p < m; p++) {
                for (int q = 0; q < n; q++) {
                    ways[step][p][q] = 0;
                    if (p != 0) {
                        ways[step][p][q] += ways[step - 1][p - 1][q];
                        ways[step][p][q] %= MOD;
                    }
                    if (p != m - 1) {
                        ways[step][p][q] += ways[step - 1][p + 1][q];
                        ways[step][p][q] %= MOD;
                    }
                    if (q != 0) {
                        ways[step][p][q] += ways[step - 1][p][q - 1];
                        ways[step][p][q] %= MOD;
                    }
                    if (q != n - 1) {
                        ways[step][p][q] += ways[step - 1][p][q + 1];
                        ways[step][p][q] %= MOD;
                    }
                }
            }
        }
        int res = 0;
        for (int t = 0; t < N; t++) {
            res += ways[t][i][j];
            res %= MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        OutOfBoundaryPaths oobp = new OutOfBoundaryPaths();
        System.out.println(oobp.findPaths(2, 2, 2, 0, 0));
        System.out.println(oobp.findPaths(2, 2, 0, 0, 0));
        System.out.println(oobp.findPaths(1, 3, 3, 0, 1));
    }
}
