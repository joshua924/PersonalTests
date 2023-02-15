package lc.sz1288;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 */
public class WallsAndGates {
  private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public void wallsAndGates(int[][] rooms) {
    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < rooms.length; i++) {
      for (int j = 0; j < rooms[0].length; j++) {
        if (rooms[i][j] == 0) {
          queue.add(new int[]{i, j});
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int m = cell[0];
      int n = cell[1];
      for (int[] dir : DIRS) {
        int x = m + dir[0];
        int y = n + dir[1];
        if (x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length && rooms[x][y] == Integer.MAX_VALUE) {
          rooms[x][y] = rooms[m][n] + 1;
          queue.offer(new int[]{x, y});
        }
      }
    }
  }

  public static void main(String[] args) {
    WallsAndGates solution = new WallsAndGates();
    int[][] rooms = {
        {2147483647, -1, 0, 2147483647},
        {2147483647, 2147483647, 2147483647, -1},
        {2147483647, -1, 2147483647, -1},
        {0, -1, 2147483647, 2147483647}};
    solution.wallsAndGates(rooms);
    System.out.println(Arrays.deepToString(rooms));
  }
}
