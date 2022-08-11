package lc.sz1288;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeadsToDestination {
  public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    List<List<Integer>> graph = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      graph.add(new LinkedList<>());
    }
    for (int[] edge : edges) {
      graph
          .get(edge[0])
          .add(edge[1]);
    }
    int[] status = new int[n];
    return leadsToDest(graph, source, destination, status);
  }

  private boolean leadsToDest(List<List<Integer>> graph, int current, int destination, int[] status) {
    if (status[current] > 0) {
      return status[current] == 2;
    }
    status[current] = 1;
    if (graph.get(current).isEmpty()) {
      return current == destination;
    }
    for (int neighbor : graph.get(current)) {
      if (!leadsToDest(graph, neighbor, destination, status)) {
        return false;
      }
    }
    status[current] = 2;
    return true;
  }

  public static void main(String[] args) {
    LeadsToDestination solution = new LeadsToDestination();
    int[][] edges = {{0, 1}, {1, 1}, {1, 2}};
    System.out.println();
    System.out.println(solution.leadsToDestination(3, edges, 0, 2));
  }
}
