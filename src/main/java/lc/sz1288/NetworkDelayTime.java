package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import lombok.ToString;

/**
 * You are given a network of n nodes, labeled from 1 to n.
 * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 */
public class NetworkDelayTime {
  public int networkDelayTime(int[][] times, int n, int k) {
    Map<Integer, List<Edge>> map = new HashMap<>();
    for (int[] time : times) {
      if (!map.containsKey(time[0])) {
        map.put(time[0], new LinkedList<>());
      }
      map.get(time[0]).add(new Edge(time[0], time[1], time[2]));
    }
    Integer[] cost = new Integer[n + 1];
    Arrays.fill(cost, Integer.MAX_VALUE);
    cost[k] = 0;
    cost[0] = 0;
    Set<Integer> visited = new HashSet<>();
    TreeSet<NodeDistance> distances =
        new TreeSet<>((n1, n2) -> n1.distance != n2.distance ? n1.distance - n2.distance : n1.node - n2.node);
    distances.add(new NodeDistance(k, 0));
    while (!distances.isEmpty()) {
      NodeDistance entry = distances.pollFirst();
      visited.add(entry.node);
      for (Edge edge : map.getOrDefault(entry.node, new ArrayList<>())) {
        int to = edge.to;
        if (!visited.contains(to)) {
          int newWeight = edge.weight + entry.distance;
          if (newWeight < cost[to]) {
            distances.remove(new NodeDistance(to, cost[to]));
            distances.add(new NodeDistance(to, newWeight));
            cost[to] = newWeight;
          }
        }
      }
    }
    Integer max = Collections.max(Arrays.asList(cost));
    return max == Integer.MAX_VALUE ? -1 : max;
  }

  @ToString
  private static class Edge {
    int from;
    int to;
    int weight;

    Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  @ToString
  private static class NodeDistance {
    int node;
    int distance;

    NodeDistance(int node, int distance) {
      this.node = node;
      this.distance = distance;
    }
  }

  public static void main(String[] args) {
    NetworkDelayTime solution = new NetworkDelayTime();
    int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
    System.out.println(solution.networkDelayTime(times, 4, 2));
    int[][] times1 = {{1, 2, 1}};
    System.out.println(solution.networkDelayTime(times1, 2, 2));
  }
}
