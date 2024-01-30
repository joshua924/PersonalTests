package lc.sz1288;

import java.util.*;

/**
 * On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
 *
 * A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
 *
 * Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone,
 * return the largest possible number of stones that can be removed.
 */
public class RemoveStones {
  public int removeStones(int[][] stones) {
    Map<Integer, List<Stone>> xToStones = new HashMap<>();
    Map<Integer, List<Stone>> yToStones = new HashMap<>();
    Stone[] stoneArray = new Stone[stones.length];
    for (int i = 0; i < stones.length; i++) {
      Stone stone = new Stone(stones[i][0], stones[i][1]);
      stoneArray[i] = stone;
      if (!xToStones.containsKey(stone.x)) {
        xToStones.put(stone.x, new LinkedList<>());
      }
      xToStones.get(stone.x).add(stone);
      if (!yToStones.containsKey(stone.y)) {
        yToStones.put(stone.y, new LinkedList<>());
      }
      yToStones.get(stone.y).add(stone);
    }

    int count = 0;
    for (Stone stone : stoneArray) {
      if (!stone.visited) {
        dfs(xToStones, yToStones, stone);
        count++;
      }
    }
    return stones.length - count;
  }

  private void dfs(Map<Integer, List<Stone>> xToStones, Map<Integer, List<Stone>> yToStones, Stone current) {
    if (current.visited) {
      return;
    }
    current.visited = true;
    Set<Stone> neighbors = new HashSet<>();
    neighbors.addAll(xToStones.get(current.x));
    neighbors.addAll(yToStones.get(current.y));
    for (Stone neighbor : neighbors) {
      dfs(xToStones, yToStones, neighbor);
    }
  }

  private static class Stone {
    int x;
    int y;
    boolean visited;

    Stone(int x, int y) {
      this.x = x;
      this.y = y;
      this.visited = false;
    }
  }

  public static void main(String[] args) {
    RemoveStones solution = new RemoveStones();
    int[][] stones = {{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}};
    System.out.println(solution.removeStones(stones));
  }
}
