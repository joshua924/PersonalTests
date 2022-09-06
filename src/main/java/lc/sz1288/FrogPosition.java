package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1.
 * In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected.
 * The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability.
 * Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
 *
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 *
 * Return the probability that after t seconds the frog is on the vertex target.
 * Answers within 10-5 of the actual answer will be accepted.
 */
public class FrogPosition {
  public double frogPosition(int n, int[][] edges, int t, int target) {
    boolean[] visited = new boolean[n + 1];
    double[] probability = new double[n + 1];
    List<List<Integer>> graph = new ArrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    List<Integer> current = new LinkedList<>();
    current.add(1);
    visited[1] = true;
    probability[1] = 1.0;
    while (!current.isEmpty() && t-- > 0) {
      List<Integer> next = new LinkedList<>();
      for (int val : current) {
        int nextCount = 0;
        for (int neighbor : graph.get(val)) {
          if (!visited[neighbor]) {
            nextCount++;
          }
        }
        for (int neighbor : graph.get(val)) {
          if (!visited[neighbor]) {
            visited[neighbor] = true;
            next.add(neighbor);
            probability[neighbor] = probability[val] / nextCount;
          }
        }
        if (nextCount > 0) {
          probability[val] = 0.0;
        }
      }
      current = next;
    }
    return probability[target];
  }

  public static void main(String[] args) {
    int[][] edges = {{2, 1}, {3, 2}};
    FrogPosition solution = new FrogPosition();
    System.out.println(solution.frogPosition(3, edges, 1, 2));
  }
}
