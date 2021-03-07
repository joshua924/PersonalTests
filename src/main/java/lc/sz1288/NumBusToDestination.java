package lc.sz1288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * <p>
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T.
 * Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 * <p>
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Note:
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 */
public class NumBusToDestination {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        Map<Integer, Set<Integer>> buses = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            for (int j = 0; j < route.length; j++) {
                buses.computeIfAbsent(route[j], n -> new HashSet<>()).add(i);
            }
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> level = new HashSet<>();
        visited.add(S);
        level.add(S);
        int num = 0;
        while (!level.isEmpty()) {
            Set<Integer> next = new HashSet<>();
            for (int stop : level) {
                if (stop == T) {
                    return num;
                }
                for (int bus : buses.get(stop)) {
                    for (int each : routes[bus]) {
                        if (visited.add(each)) {
                            next.add(each);
                        }
                    }
                }
            }
            level = next;
            num++;
        }
        return -1;
    }

    public static void main(String[] args) {
        NumBusToDestination nbtd = new NumBusToDestination();
        int[][] routes1 = {{1, 2, 7}, {3, 6, 7}};
        System.out.println(nbtd.numBusesToDestination(routes1, 1, 6));
        int[][] routes2 = {{1, 2, 5}, {3, 6, 7}};
        System.out.println(nbtd.numBusesToDestination(routes2, 1, 6));
    }
}
