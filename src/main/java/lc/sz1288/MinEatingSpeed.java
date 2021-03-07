package lc.sz1288;

import java.util.stream.IntStream;

/**
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of
 * them instead, and won't eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
 * <p>
 * Return the minimum integer K such that she can eat all the bananas within H hours.
 */
public class MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int H) {
        int max = IntStream.of(piles).max().getAsInt();
        int low = 1;
        int high = max;
        while (low < high) {
            int mid = (low + high) / 2;
            if (canFinish(piles, mid, H)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canFinish(int[] piles, int speed, int time) {
        int total = 0;
        for (int pile : piles) {
            total += Math.max(1, pile / speed);
            if (total > time) {
                return false;
            }
        }
        return true;
    }
}
