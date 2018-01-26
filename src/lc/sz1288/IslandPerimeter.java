package lc.sz1288;

public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                res += count(grid, i, j, m, n);
            }
        }
        return res;
    }

    private int count(int[][] grid, int i, int j, int m, int n) {
        int count = 0;
        if (i == m - 1 || grid[i + 1][j] == 0) {
            count += 1;
        }
        if (i == 0 || grid[i - 1][j] == 0) {
            count += 1;
        }
        if (j == n - 1 || grid[i][j + 1] == 0) {
            count += 1;
        }
        if (j == 0 || grid[i][j - 1] == 0) {
            count += 1;
        }
        return count;
    }
}
