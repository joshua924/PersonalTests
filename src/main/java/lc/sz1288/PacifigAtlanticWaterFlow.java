package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * <p>
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * <p>
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * <p>
 * Note:
 * 1. The order of returned grid coordinates does not matter.
 * 2. Both m and n are less than 150.
 */
public class PacifigAtlanticWaterFlow {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return res;
        }
        int n = matrix[0].length;
        boolean[][] pacificFlows = new boolean[m][n];
        boolean[][] atlanticFlows = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (!pacificFlows[i][0]) {
                dfs(matrix, pacificFlows, i, 0);
            }
            if (!atlanticFlows[i][n - 1]) {
                dfs(matrix, atlanticFlows, i, n - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (!pacificFlows[0][i]) {
                dfs(matrix, pacificFlows, 0, i);
            }
            if (!atlanticFlows[m - 1][i]) {
                dfs(matrix, atlanticFlows, m - 1, i);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificFlows[i][j] && atlanticFlows[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        for (int[] dir : DIRS) {
            int newX = dir[0] + x;
            int newY = dir[1] + y;
            if (newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix[0].length || visited[newX][newY] || matrix[x][y] > matrix[newX][newY]) {
                continue;
            }
            dfs(matrix, visited, newX, newY);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        PacifigAtlanticWaterFlow paw = new PacifigAtlanticWaterFlow();
        paw.pacificAtlantic(matrix).forEach(arr -> System.out.println(Arrays.toString(arr)));
        paw.pacificAtlantic(new int[][]{}).forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
}
