package lc.sz1288;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlights {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        Map<Integer, Integer> best = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        best.put(src, 0);
        pq.offer(new int[]{0, 0, src});

        while (!pq.isEmpty()) {
            int[] info = pq.poll();
            int cost = info[0];
            int k = info[1];
            int city = info[2];
            if (k > K + 1 || cost > best.getOrDefault(k * 1000 + city, Integer.MAX_VALUE)) {
                continue;
            }
            if (city == dst) {
                return cost;
            }
            for (int next = 0; next < n; next++) {
                int nextCost = graph[city][next];
                if (nextCost != 0) {
                    int newCost = cost + nextCost;
                    if (newCost < best.getOrDefault((k + 1) * 1000 + city, Integer.MAX_VALUE)) {
                        pq.offer(new int[]{newCost, k + 1, next});
                    }
                }
            }
        }
        return -1;
    }

    public int findCheapestPrice_awesome(int n, int[][] flights, int src, int dst, int K) {
        int[][] dist = new int[2][n];
        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        dist[0][src] = 0;
        dist[1][src] = 0;

        for (int i = 0; i <= K; ++i) {
            for (int[] edge : flights) {
                int current = i % 2;
                int next = 1 - current;
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];
                dist[next][to] = Math.min(dist[next][to], dist[current][from] + cost);
            }
        }
        int res = dist[1 - K % 2][dst];
        return res < INF ? res : -1;
    }

    public static void main(String[] args) {
        CheapestFlights cf = new CheapestFlights();
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println(cf.findCheapestPrice(3, flights, 0, 2, 0));
        System.out.println(cf.findCheapestPrice(3, flights, 0, 2, 1));
    }
}
