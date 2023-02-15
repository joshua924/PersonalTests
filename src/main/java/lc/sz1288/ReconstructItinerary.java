package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReconstructItinerary {
  private boolean found = false;
  private List<String> result;

  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, LinkedList<String>> edges = new HashMap<>();
    for (List<String> ticket : tickets) {
      String source = ticket.get(0);
      String dest = ticket.get(1);
      if (!edges.containsKey(source)) {
        edges.put(source, new LinkedList<>());
      }
      edges.get(source).add(dest);
    }
    edges.values().forEach(list -> list.sort(Comparator.naturalOrder()));

    LinkedList<String> path = new LinkedList<>();
    path.add("JFK");
    dfs(edges, "JFK", path, tickets.size() + 1);
    return result;
  }

  private void dfs(Map<String, LinkedList<String>> edges, String current, LinkedList<String> path,
                   int targetSize) {
    if (found) {
      return;
    }
    if (path.size() == targetSize) {
      found = true;
      result = new ArrayList<>(path);
      return;
    }
    LinkedList<String> neighbors = edges.get(current);
    if (neighbors == null || neighbors.isEmpty()) {
      return;
    }
    List<String> copy = new ArrayList<>(neighbors);
    for (int i = 0; i < copy.size(); i++) {
      String next = copy.get(i);
      neighbors.remove(i);
      path.add(next);
      dfs(edges, next, path, targetSize);
      path.removeLast();
      neighbors.add(i, next);
    }
  }

  public static void main(String[] args) {
    ReconstructItinerary solution = new ReconstructItinerary();
//    System.out.println(solution.findItinerary(Arrays.asList(
//        Arrays.asList("JFK", "SFO"),
//        Arrays.asList("JFK", "ATL"),
//        Arrays.asList("SFO", "ATL"),
//        Arrays.asList("ATL", "JFK"),
//        Arrays.asList("ATL", "SFO")
//    )));
    System.out.println(solution.findItinerary(Arrays.asList(
        Arrays.asList("JFK", "KUL"),
        Arrays.asList("JFK", "NRT"),
        Arrays.asList("NRT", "JFK")
    )));
  }
}
