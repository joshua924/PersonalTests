package lc.sz1288;

import java.util.TreeMap;

/**
 * Alice has a hand of cards, given as an array of integers.
 * <p>
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * <p>
 * Return true if and only if she can.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * Example 2:
 * <p>
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 */
public class IsNStraightHand {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }
        TreeMap<Integer, Integer> cards = new TreeMap<>();
        for (int card : hand) {
            cards.put(card, cards.getOrDefault(card, 0) + 1);
        }
        while (!cards.isEmpty()) {
            int start = cards.firstKey();
            for (int i = start; i < start + W; i++) {
                if (!cards.containsKey(i)) {
                    return false;
                }
                if (cards.get(i) > 1) {
                    cards.put(i, cards.get(i) - 1);
                } else {
                    cards.remove(i);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsNStraightHand ins = new IsNStraightHand();
        System.out.println(ins.isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        System.out.println(ins.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(ins.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 1));
    }
}
