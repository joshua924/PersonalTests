package lc.sz1288;

public class NumberOfIslands {
  private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int numIslands(char[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    boolean[][] visited = new boolean[m][n];
    int result = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1' && !visited[i][j]) {
          dfs(grid, visited, i, j);
          result++;
        }
      }
    }

    return result;
  }

  private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
    if (visited[i][j] || grid[i][j] != '1') {
      return;
    }
    visited[i][j] = true;
    for (int[] dir : DIRS) {
      int newI = i + dir[0];
      int newJ = j + dir[1];
      if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length) {
        dfs(grid, visited, newI, newJ);
      }
    }
  }

  public static void main(String[] args) {
    NumberOfIslands solution = new NumberOfIslands();
    char[][] grid = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
    System.out.println(solution.numIslands(grid));
  }
}
