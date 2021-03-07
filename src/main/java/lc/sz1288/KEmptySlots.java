package lc.sz1288;

import java.util.TreeSet;

/**
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days.
 * In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 * <p>
 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
 * <p>
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
 * <p>
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming,
 * and also the number of flowers between them is k and these flowers are not blooming.
 * <p>
 * If there isn't such day, output -1.
 */
public class KEmptySlots {
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> bloomingSlots = new TreeSet<>();
        for (int day = 1; day <= flowers.length; day++) {
            int slot = flowers[day - 1];
            Integer lower = bloomingSlots.lower(slot);
            Integer higher = bloomingSlots.higher(slot);
            bloomingSlots.add(slot);
            if (lower != null && slot - lower - 1 == k || higher != null && higher - slot - 1 == k) {
                return day;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        KEmptySlots kes = new KEmptySlots();
        System.out.println(kes.kEmptySlots(new int[]{1, 3, 2}, 1));
        System.out.println(kes.kEmptySlots(new int[]{1, 2, 3}, 1));
    }
}
