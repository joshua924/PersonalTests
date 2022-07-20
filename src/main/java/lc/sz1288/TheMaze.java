package lc.sz1288;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1).
 * The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol],
 * return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 */
public class TheMaze {
  private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public boolean hasPath(int[][] maze, int[] start, int[] destination) {
    int m = maze.length;
    int n = maze[0].length;

    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[m][n];
    queue.add(start);

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int i = current[0];
      int j = current[1];
      if (i == destination[0] && j == destination[1]) {
        return true;
      }
      if (visited[i][j]) {
        continue;
      }
      visited[i][j] = true;
      for (int[] nextMove : nextCells(maze, i, j)) {
        queue.offer(nextMove);
      }
    }
    return false;
  }

  private List<int[]> nextCells(int[][] maze, int i, int j) {
    List<int[]> res = new ArrayList<>();
    for (int[] dir : DIRS) {
      int x = i;
      int y = j;
      int newX = x + dir[0];
      int newY = y + dir[1];
      while (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length && maze[newX][newY] == 0) {
        x = newX;
        y = newY;
        newX += dir[0];
        newY += dir[1];
      }
      if (x != i || y != j) {
        res.add(new int[]{x, y});
      }
    }
    return res;
  }

  public static void main(String[] args) {
    TheMaze solution = new TheMaze();
    int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0,
        0, 0}};
    int[] start = {0, 4};
    int[] end = {4, 4};
    System.out.println(solution.hasPath(maze, start, end));
    System.out.println(solution.hasPath(maze, start, new int[]{3, 2}));
  }
}
