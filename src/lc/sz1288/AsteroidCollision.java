package lc.sz1288;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> remains = new LinkedList<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                remains.offer(asteroid);
            } else {
                int negative = -asteroid;
                while (!remains.isEmpty() && negative > 0 && remains.peekLast() > 0) {
                    int next = remains.removeLast();
                    if (next > negative) {
                        remains.offer(next);
                        negative = 0;
                    } else if (negative == next) {
                        negative = 0;
                    }
                }
                if (negative > 0) {
                    remains.offer(-negative);
                }
            }
        }
        return remains.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        AsteroidCollision ac = new AsteroidCollision();
        int[] asteroids;
//        asteroids = new int[]{1, -2, -5};
//        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids)));
//        asteroids = new int[]{8, -8};
//        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids)));
//        asteroids = new int[]{5, 10, -5};
//        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids)));
        asteroids = new int[]{-2, -2, 1, -2};
        System.out.println(Arrays.toString(ac.asteroidCollision(asteroids)));
    }
}
