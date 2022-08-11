package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 *
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...].
 * Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list,
 * which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends.
 * Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 */
public class GetSkyline {
  public List<List<Integer>> getSkyline(int[][] buildings) {
    List<List<Integer>> edges = new ArrayList<>();
    for (int i = 0; i < buildings.length; i++) {
      edges.add(Arrays.asList(buildings[i][0], i));
      edges.add(Arrays.asList(buildings[i][1], i));
    }
    edges.sort(Comparator.comparingInt(a -> a.get(0)));

    Queue<List<Integer>> live = new PriorityQueue<>((a, b) -> b.get(0) - a.get(0));
    LinkedList<List<Integer>> answer = new LinkedList<>();
    int index = 0;
    while (index < edges.size()) {
      int curr = edges.get(index).get(0);

      while (index < edges.size() && edges.get(index).get(0) == curr) {
        int pos = edges.get(index).get(1);
        if (buildings[pos][0] == curr) {
          live.offer(Arrays.asList(buildings[pos][2], buildings[pos][1]));
        }
        index++;
      }

      while (!live.isEmpty() && live.peek().get(1) <= curr) {
        live.poll();
      }
      int height = live.isEmpty() ? 0 : live.peek().get(0);
      if (answer.isEmpty() || answer.getLast().get(1) != height) {
        answer.add(Arrays.asList(curr, height));
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    GetSkyline solution = new GetSkyline();
    int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
    System.out.println(solution.getSkyline(buildings));
  }
}
