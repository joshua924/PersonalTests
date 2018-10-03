package lc.sz1288;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] max = new int[1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, max, 1, i, j, m, n);
                }
            }
        }
        return max[0];
    }

    private int dfs(int[][] grid, int[] max, int currentArea, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return 0;
        }
        int area = 1;
        grid[i][j] = 0;
        area += dfs(grid, max, currentArea + 1, i - 1, j, m, n);
        area += dfs(grid, max, currentArea + 1, i + 1, j, m, n);
        area += dfs(grid, max, currentArea + 1, i, j - 1, m, n);
        area += dfs(grid, max, currentArea + 1, i, j + 1, m, n);
        max[0] = Math.max(max[0], area);
        return area;
    }
}
