package lc.sz1288;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B
 * such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 */
public class IsBipartite {
  public boolean isBipartite(int[][] graph) {
    Map<Integer, Character> sides = new HashMap<>();
    AtomicBoolean valid = new AtomicBoolean(true);

    for (int i = 0; i < graph.length; i++) {
      if (!sides.containsKey(i)) {
        dfs(i, graph, 'A', sides, valid);
      }
    }

    return valid.get() && sides.size() == graph.length;
  }

  private void dfs(int node, int[][] graph, char side, Map<Integer, Character> sides,
                   AtomicBoolean valid) {
    sides.put(node, side);
    for (int neighbor : graph[node]) {
      char nextSide = side == 'A' ? 'B' : 'A';
      if (sides.containsKey(neighbor)) {
        if (sides.get(neighbor) != nextSide) {
          valid.set(false);
          return;
        }
      } else {
        dfs(neighbor, graph, nextSide, sides, valid);
      }
    }
  }

  public static void main(String[] args) {
    IsBipartite solution = new IsBipartite();
    int[][] graph1 = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
    System.out.println(solution.isBipartite(graph1));
    int[][] graph2 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
    System.out.println(solution.isBipartite(graph2));
  }
}
