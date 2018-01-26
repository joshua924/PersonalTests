package lc.sz1288;

public class OutOfBoundaryPaths {
    private static final int MOD = 1000000007;

    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] ways = new int[2][m][n];
        int current = 0;
        for (int p = 0; p < m; p++) {
            ways[current][p][0]++;
            ways[current][p][n - 1]++;
        }
        for (int q = 0; q < n; q++) {
            ways[current][0][q]++;
            ways[current][m - 1][q]++;
        }
        for (int step = 2; step <= N; step++) {
            int previous = current;
            current = 1 - current;
            for (int p = 0; p < m; p++) {
                for (int q = 0; q < n; q++) {
                    ways[current][p][q] = ways[previous][p][q];
                    if (p != 0) {
                        ways[current][p][q] += ways[previous][p - 1][q];
                        ways[current][p][q] %= MOD;
                    }
                    if (p != m - 1) {
                        ways[current][p][q] += ways[previous][p + 1][q];
                        ways[current][p][q] %= MOD;
                    }
                    if (q != 0) {
                        ways[current][p][q] += ways[previous][p][q - 1];
                        ways[current][p][q] %= MOD;
                    }
                    if (q != n - 1) {
                        ways[current][p][q] += ways[previous][p][q + 1];
                        ways[current][p][q] %= MOD;
                    }
                }
            }
        }
        return ways[current][i][j];
    }

    public static void main(String[] args) {
        OutOfBoundaryPaths oobp = new OutOfBoundaryPaths();
        System.out.println(oobp.findPaths(2, 2, 2, 0, 0));
    }
}
