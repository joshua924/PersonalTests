package lc.sz1288;

/**
 * You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
 * This area is in the shape of a circle with the center as the location of the bomb.
 *
 * The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri].
 * xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.
 *
 * You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range.
 * These bombs will further detonate the bombs that lie in their ranges.
 *
 * Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.
 */
public class MaximumBombs {
  public int maximumDetonation(int[][] bombs) {
    int result = 0;
    for (int i = 0; i < bombs.length; i++) {
      result = Math.max(result, dfs(i, bombs, new boolean[bombs.length]));
    }
    return result;
  }

  private int dfs(int idx, int[][] bombs, boolean[] visited) {
    int count = 0;
    visited[idx] = true;
    for (int i = 0; i < bombs.length; i++) {
      if (!visited[i] && inRange(bombs[idx], bombs[i])) {
        count += dfs(i, bombs, visited);
      }
    }
    return 1 + count;
  }

  private boolean inRange(int[] a, int[] b) {
    long dx = a[0] - b[0];
    long dy = a[1] - b[1];
    return dx * dx + dy * dy <= (long) a[2] * a[2];
  }

  public static void main(String[] args) {
    MaximumBombs solution = new MaximumBombs();
    int[][] bombs = {{1, 1, 100000}, {100000, 100000, 1}};
    System.out.println(solution.maximumDetonation(bombs));
    int[][] bombs1 = {{2, 4, 3}, {2, 4, 3}};
    System.out.println(solution.maximumDetonation(bombs1));
  }
}
