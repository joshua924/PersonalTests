package lc.sz1288;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * <p>
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 */
public class LongestIncreasingPath {
  private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public int longestIncreasingPath(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int[][] cache = new int[matrix.length][matrix[0].length];
    int max = 1;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (cache[i][j] == 0) {
          max = Integer.max(max, dfs(matrix, i, j, cache));
        }
      }
    }
    return max;
  }

  public int dfs(int[][] matrix, int i, int j, int[][] cache) {
    if (cache[i][j] != 0) {
      return cache[i][j];
    }
    int max = 1;
    for (int[] dir : DIRS) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] <= matrix[i][j]) {
        continue;
      }
      max = Integer.max(max, 1 + dfs(matrix, x, y, cache));
    }
    cache[i][j] = max;
    return max;
  }

  public static void main(String[] args) {
    LongestIncreasingPath solution = new LongestIncreasingPath();
    int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
    System.out.println(solution.longestIncreasingPath(matrix));
  }
}
