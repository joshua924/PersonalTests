package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestToAllBuilding {
  private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public int shortestDistance(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[][][] totalDistance = new int[2][m][n];
    int houses = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          houses++;
          bfs(grid, i, j, m, n, totalDistance);
        }
      }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (totalDistance[0][i][j] == houses && grid[i][j] == 0) {
          min = Integer.min(min, totalDistance[1][i][j]);
        }
      }
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private void bfs(int[][] grid, int i, int j, int m, int n, int[][][] distances) {
    boolean[][] visited = new boolean[m][n];
    int distance = 0;
    List<Integer> current = new ArrayList<>();
    current.add(i * n + j);
    while (!current.isEmpty()) {
      List<Integer> next = new ArrayList<>();
      for (int each : current) {
        int x = each / n;
        int y = each % n;
        if (visited[x][y]) {
          continue;
        }
        visited[x][y] = true;
        distances[0][x][y]++;
        distances[1][x][y] += distance;
        for (int[] dir : DIRS) {
          int newX = x + dir[0];
          int newY = y + dir[1];
          if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 0 && !visited[newX][newY]) {
            next.add(newX * n + newY);
          }
        }
      }
      current = next;
      distance++;
    }
  }

  public static void main(String[] args) {
    ShortestToAllBuilding solution = new ShortestToAllBuilding();
    int[][] grid = {
        {1, 0, 2, 0, 1},
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0}
    };
    System.out.println(solution.shortestDistance(grid));
    System.out.println(solution.shortestDistance(new int[][]{{0, 1}}));
    System.out.println(solution.shortestDistance(new int[][]{{1}}));
  }
}
