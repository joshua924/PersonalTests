package lc.sz1288;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import javafx.util.Pair;

/**
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 * <p>
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query
 * where you must find the answer for Cj / Dj = ?.
 * <p>
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * <p>
 * Note: The input is always valid. You may assume that evaluating the queries
 * will not result in division by zero and that there is no contradiction.
 */
public class EvaluateDivision {
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    Map<String, Map<String, Double>> edges = new HashMap<>();
    for (int i = 0; i < equations.size(); i++) {
      List<String> equation = equations.get(i);
      String from = equation.get(0);
      String to = equation.get(1);
      if (!edges.containsKey(from)) {
        edges.put(from, new HashMap<>());
      }
      if (!edges.containsKey(to)) {
        edges.put(to, new HashMap<>());
      }
      edges.get(from).put(to, values[i]);
      edges.get(to).put(from, 1 / values[i]);
    }

    double[] result = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      result[i] = bfs(edges, query.get(0), query.get(1));
    }
    return result;
  }

  private double bfs(Map<String, Map<String, Double>> edges, String source, String target) {
    if (!edges.containsKey(source)) {
      return -1.0;
    }
    Queue<Pair<String, Double>> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.offer(new Pair<>(source, 1.0));
    while (!queue.isEmpty()) {
      Pair<String, Double> pair = queue.poll();
      String node = pair.getKey();
      double val = pair.getValue();
      if (target.equals(node)) {
        return val;
      }
      visited.add(node);
      Map<String, Double> neighbors = edges.get(node);
      neighbors.forEach(
          (neighbor, weight) -> {
            double newWeight = val * weight;
            if (!visited.contains(neighbor)) {
              queue.offer(new Pair<>(neighbor, newWeight));
            }
          });
    }
    return -1.0;
  }

  public static void main(String[] args) {
    EvaluateDivision solution = new EvaluateDivision();
    List<List<String>> equations = Arrays.asList(
        Arrays.asList("a", "b"),
        Arrays.asList("b", "c"),
        Arrays.asList("bc", "cd")
    );
    double[] values = {2.0, 2.5, 5.0};
    List<List<String>> queries = Arrays.asList(
        Arrays.asList("a", "c"),
        Arrays.asList("c", "b"),
        Arrays.asList("bc", "cd"),
        Arrays.asList("cd", "bc")
    );
    System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));
  }
}
