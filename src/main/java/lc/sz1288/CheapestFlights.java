package lc.sz1288;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
 * <p>
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such
 * route, output -1.
 * <p>
 * Follow up: also return the whole route for the cheapest itinerary.
 */
public class CheapestFlights {
    public int findCheapestPrice_awesome(int n, int[][] flights, int src, int dst, int K) {
        int[][] dist = new int[3][n];
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
                int newCost = dist[current][from] + cost;
                if (newCost < dist[next][to]) {
                    dist[next][to] = newCost;
                    dist[2][to] = from;
                }
            }
        }
        int res = dist[1 - K % 2][dst];
        return res < INF ? res : -1;
    }

    public List<Integer> findCheapestPrice_returnRoute(int n, int[][] flights, int src, int dst, int K) {
        int[][] dist = new int[3][n];
        int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dist[0], INF);
        Arrays.fill(dist[1], INF);
        Arrays.fill(dist[2], INF);
        dist[0][src] = 0;
        dist[1][src] = 0;

        for (int i = 0; i <= K; ++i) {
            for (int[] edge : flights) {
                int current = i % 2;
                int next = 1 - current;
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];
                int newCost = dist[current][from] + cost;
                if (newCost < dist[next][to]) {
                    dist[next][to] = newCost;
                    dist[2][to] = from;
                }
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (dst != INF) {
            res.push(dst);
            dst = dist[2][dst];
        }
        return res.size() == 1 ? Collections.emptyList() : res;
    }

    public static void main(String[] args) {
        CheapestFlights cf = new CheapestFlights();
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        System.out.println(cf.findCheapestPrice_awesome(3, flights, 0, 2, 0));
        System.out.println(cf.findCheapestPrice_awesome(3, flights, 0, 2, 1));
        System.out.println(cf.findCheapestPrice_returnRoute(3, flights, 0, 2, 0));
        System.out.println(cf.findCheapestPrice_returnRoute(3, flights, 0, 2, 1));
    }
}
