package lc.sz1288;

import java.util.*;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 */
public class NumBusesToDestination {
  public int numBusesToDestination(int[][] routes, int source, int target) {
    if (source == target) {
      return 0;
    }
    List<List<Integer>> graph = new ArrayList<>();
    for (int[] route : routes) {
      Arrays.sort(route);
      graph.add(new ArrayList<>());
    }
    Set<Integer> seen = new HashSet<>();
    Set<Integer> targets = new HashSet<>();
    List<Integer> current = new ArrayList<>();
    int numBus = 1;

    for (int i = 0; i < graph.size(); i++) {
      for (int j = i + 1; j < graph.size(); j++) {
        if (intersect(routes[i], routes[j])) {
          graph.get(i).add(j);
          graph.get(j).add(i);
        }
      }
      for (int stop : routes[i]) {
        if (stop == source) {
          current.add(i);
        }
        if (stop == target) {
          targets.add(i);
        }
      }
    }

    while (!current.isEmpty()) {
      List<Integer> next = new ArrayList<>();
      for (int bus : current) {
        if (targets.contains(bus)) {
          return numBus;
        }
        if (seen.contains(bus)) {
          continue;
        }
        seen.add(bus);
        next.addAll(graph.get(bus));
      }
      numBus++;
      current = next;
    }
    return -1;
  }

  private boolean intersect(int[] r1, int[] r2) {
    int i = 0;
    int j = 0;
    while (i < r1.length && j < r2.length) {
      if (r1[i] == r2[j]) {
        return true;
      }
      if (r1[i] < r2[j]) {
        i++;
      } else {
        j++;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    NumBusesToDestination solution = new NumBusesToDestination();
    int[][] routes = {{1, 3, 7}, {7, 10, 12}, {12, 15}, {1, 6, 8, 9, 15}};
    System.out.println(solution.numBusesToDestination(routes, 3, 15));
  }
}
