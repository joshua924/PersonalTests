package lc.sz1288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 */
public class LargestIsland {
  private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public int largestIsland(int[][] grid) {
    int n = grid.length;
    Map<Integer, Integer> sizes = new HashMap<>();
    int groupId = 2;
    int max = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          int size = dfs(grid, i, j, groupId);
          sizes.put(groupId, size);
          max = Math.max(max, size);
          groupId++;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          int connected = 1;
          Set<Integer> connectedGroups = new HashSet<>();
          for (int[] dir : DIRS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI < n && newI >= 0 && newJ < n && newJ >= 0 && grid[newI][newJ] > 1) {
              connectedGroups.add(grid[newI][newJ]);
            }
          }
          for (int group : connectedGroups) {
            connected += sizes.get(group);
          }
          max = Math.max(max, connected);
        }
      }
    }
    return max;
  }

  private int dfs(int[][] grid, int i, int j, int groupId) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] != 1) {
      return 0;
    }
    grid[i][j] = groupId;
    int size = 1;
    for (int[] dir : DIRS) {
      size += dfs(grid, i + dir[0], j + dir[1], groupId);
    }
    return size;
  }

  public static void main(String[] args) {
    LargestIsland solution = new LargestIsland();
    int[][] grid = {
        {0, 1, 0},
        {1, 1, 0},
        {0, 0, 1}
    };
    System.out.println(solution.largestIsland(grid));
    System.out.println(solution.largestIsland(new int[][]{{1, 1}, {1, 1}}));
  }
}
