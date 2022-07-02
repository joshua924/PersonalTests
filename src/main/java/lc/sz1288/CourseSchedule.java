package lc.sz1288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class CourseSchedule {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> edges = new HashMap<>();
    for (int[] pair : prerequisites) {
      Set<Integer> pres = edges.getOrDefault(pair[0], new HashSet<>());
      pres.add(pair[1]);
      edges.put(pair[0], pres);
    }

    Set<Integer> visited = new HashSet<>();
    for (int course = 0; course < numCourses; course++) {
      if (!visited.contains(course) && hasCircle(course, edges, new HashSet<>(), visited)) {
        return false;
      }
    }
    return true;
  }

  private boolean hasCircle(int course, Map<Integer, Set<Integer>> edges, Set<Integer> visited,
                            Set<Integer> beingVisited) {
    beingVisited.add(course);
    for (int next : edges.getOrDefault(course, new HashSet<>())) {
      // found circle
      if (beingVisited.contains(next)) {
        return true;
      }
      // if we find a new neighbor, recurse on it
      if (!visited.contains(next) && hasCircle(next, edges, visited, beingVisited)) {
        return true;
      }
    }
    beingVisited.remove(course);
    visited.add(course);
    return false;
  }

  public static void main(String[] args) {
    CourseSchedule solution = new CourseSchedule();
    System.out.println(solution.canFinish(2, new int[][]{{1, 0}}));
    System.out.println(solution.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
  }
}
