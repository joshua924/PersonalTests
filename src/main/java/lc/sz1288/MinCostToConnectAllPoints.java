package lc.sz1288;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane,
 * where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
 * where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected.
 * All points are connected if there is exactly one simple path between any two points.
 */
public class MinCostToConnectAllPoints {
  public int minCostConnectPoints(int[][] points) {
    if (points == null || points.length == 0) {
      return 0;
    }
    int n = points.length;
    PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
    boolean[] visited = new boolean[n];
    int result = 0;
    int count = n - 1;
    int[] coordinate1 = points[0];
    for (int j = 1; j < n; j++) {
      int[] coordinate2 = points[j];
      int cost = Math.abs(coordinate1[0] - coordinate2[0]) +
          Math.abs(coordinate1[1] - coordinate2[1]);
      Edge edge = new Edge(0, j, cost);
      pq.add(edge);
    }
    visited[0] = true;

    while (!pq.isEmpty() && count > 0) {
      Edge edge = pq.poll();
      int to = edge.to;
      if (!visited[to]) {
        result += edge.cost;
        visited[to] = true;
        for (int j = 0; j < n; j++) {
          if (!visited[j]) {
            int distance = Math.abs(points[to][0] - points[j][0]) +
                Math.abs(points[to][1] - points[j][1]);
            pq.add(new Edge(to, j, distance));
          }
        }
        count--;
      }
    }
    return result;
  }

  private static class Edge {
    int from;
    int to;
    int cost;

    Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  public static void main(String[] args) {
    MinCostToConnectAllPoints solution = new MinCostToConnectAllPoints();
    int[][] points = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
    System.out.println(solution.minCostConnectPoints(points));
  }
}
