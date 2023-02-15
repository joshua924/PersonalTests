package lc.sz1288;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 */
public class Candy {
  public int candy(int[] ratings) {
    PriorityQueue<Integer> index = new PriorityQueue<>(Comparator.comparingInt(i -> ratings[i]));
    int[] candies = new int[ratings.length];
    for (int i = 0; i < ratings.length; i++) {
      index.offer(i);
    }
    int total = 0;
    while (!index.isEmpty()) {
      int current = index.poll();
      int candy = 1;
      if (current > 0 && ratings[current] > ratings[current - 1]) {
        candy = Integer.max(candy, candies[current - 1] + 1);
      }
      if (current < ratings.length - 1 && ratings[current] > ratings[current + 1]) {
        candy = Integer.max(candy, candies[current + 1] + 1);
      }
      candies[current] = candy;
      total += candy;
    }
    return total;
  }

  public static void main(String[] args) {
    Candy candy = new Candy();
    System.out.println(candy.candy(new int[]{1, 0, 2}));
    System.out.println(candy.candy(new int[]{1, 2, 2}));
  }
}
