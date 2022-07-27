package lc.sz1288;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 *
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 *
 * You may change 0's to 1's to connect the two islands to form one island.
 *
 * Return the smallest number of 0's you must flip to connect the two islands.
 */
public class ShortestBridge {
  private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public int shortestBridge(int[][] grid) {
    int n = grid.length;
    boolean[][] visited = new boolean[n][n];
    List<int[]> island = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1 && !visited[i][j]) {
          dfs(grid, i, j, visited, island);
          break;
        }
      }
      if (!island.isEmpty()) {
        break;
      }
    }

    int step = 0;
    while (!island.isEmpty()) {
      List<int[]> next = new LinkedList<>();
      for (int[] cell : island) {
        for (int[] dir : DIRS) {
          int x = cell[0] + dir[0];
          int y = cell[1] + dir[1];
          if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
            if (grid[x][y] == 1) {
              return step;
            }
            visited[x][y] = true;
            next.add(new int[]{x, y});
          }
        }
      }
      island = next;
      step++;
    }
    return step;
  }

  private void dfs(int[][] grid, int i, int j, boolean[][] visited, List<int[]> island) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1 || visited[i][j]) {
      return;
    }
    visited[i][j] = true;
    island.add(new int[]{i, j});
    for (int[] dir : DIRS) {
      int x = i + dir[0];
      int y = j + dir[1];
      dfs(grid, x, y, visited, island);
    }
  }

  public static void main(String[] args) {
    ShortestBridge solution = new ShortestBridge();
    int[][] grid = {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}};
    System.out.println(solution.shortestBridge(grid));
  }
}
