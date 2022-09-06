package lc.sz1288;

/**
 * There are n cities connected by some number of flights.
 * You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 */

import java.util.HashMap;
import java.util.Map;

public class FindCheapestFlight {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
    for (int[] flight : flights) {
      int from = flight[0];
      int to = flight[1];
      int price = flight[2];
      if (!prices.containsKey(from)) {
        prices.put(from, new HashMap<>());
      }
      prices.get(from).put(to, price);
    }

    Map<Integer, Integer> minPrices = new HashMap<>();
    minPrices.put(src, 0);
    for (int i = 0; i <= k; i++) {
      Map<Integer, Integer> nextPrices = new HashMap<>(minPrices);
      for (int current : minPrices.keySet()) {
        int currentPrice = minPrices.get(current);
        if (!prices.containsKey(current)) {
          continue;
        }
        Map<Integer, Integer> map = prices.get(current);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          int next = entry.getKey();
          int price = entry.getValue();
          int newPrice = price + currentPrice;
          nextPrices.put(next, Integer.min(newPrice, minPrices.getOrDefault(next, Integer.MAX_VALUE)));
        }
      }
      minPrices = nextPrices;
    }
    return minPrices.getOrDefault(dst, -1);
  }

  public static void main(String[] args) {
    FindCheapestFlight solution = new FindCheapestFlight();
  }
}
