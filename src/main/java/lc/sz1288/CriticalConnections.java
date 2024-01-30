package lc.sz1288;

import java.util.*;

/**
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server
 * connections forming a network, where connections[i] = [ai, bi] represents a connection between
 * servers ai and bi.
 * Any server can reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 */
public class CriticalConnections {
  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (List<Integer> connection : connections) {
      int i = connection.get(0);
      int j = connection.get(1);
      if (!graph.containsKey(i)) {
        graph.put(i, new ArrayList<>());
      }
      if (!graph.containsKey(j)) {
        graph.put(j, new ArrayList<>());
      }
      graph.get(i).add(j);
      graph.get(j).add(i);
    }

    Set<List<Integer>> result = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (!graph.containsKey(i)) {
        continue;
      }
      if (graph.get(i).size() == 1) {
        int j = graph.get(i).get(0);
        result.add(Arrays.asList(Integer.min(i, j), Integer.max(i, j)));
      }
    }
    return new ArrayList<>(result);
  }

  public static void main(String[] args) {
    CriticalConnections solution = new CriticalConnections();
    System.out.println(solution.criticalConnections(5, Arrays.asList(
        Arrays.asList(0, 1),
        Arrays.asList(1, 2),
        Arrays.asList(2, 0),
        Arrays.asList(1, 3)
    )));
    System.out.println(solution.criticalConnections(2, Collections.singletonList(
        Arrays.asList(0, 1)
    )));
  }
}
