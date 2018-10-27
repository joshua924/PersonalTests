package lc.sz1288;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * <p>
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * <p>
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 * <p>
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 * <p>
 * What is the total amount of fruit you can collect with this procedure?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 * <p>
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 * <p>
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 * <p>
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 */
public class TotalFruit {
    public int totalFruit(int[] tree) {
        if (tree.length <= 2) {
            return tree.length;
        }
        Set<Integer> fruits = new HashSet<>();
        int tail = 0;
        int max = 0;
        for (int head = 0; head < tree.length; head++) {
            int fruit = tree[head];
            if (fruits.contains(fruit) || fruits.size() < 2) {
                fruits.add(fruit);
                max = Math.max(max, head - tail + 1);
            } else {
                tail = head - 1;
                while (tail == 0 || tree[tail - 1] == tree[tail]) {
                    tail--;
                }
                fruits = new HashSet<>(Arrays.asList(fruit, tree[tail]));
            }
        }
        return max;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char[] arr = s.toCharArray();
        Set<Character> chars = new HashSet<>();
        int tail = 0;
        int max = 0;
        for (int head = 0; head < arr.length; head++) {
            char ch = arr[head];
            if (chars.contains(ch) || chars.size() < 2) {
                chars.add(ch);
                max = Math.max(max, head - tail + 1);
            } else {
                tail = head - 1;
                while (tail == 0 || arr[tail - 1] == arr[tail]) {
                    tail--;
                }
                chars = new HashSet<>(Arrays.asList(ch, arr[tail]));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        TotalFruit tf = new TotalFruit();
        System.out.println(tf.totalFruit(new int[]{1, 2, 1}));
        System.out.println(tf.totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(tf.totalFruit(new int[]{1, 2, 3, 2, 2}));
        System.out.println(tf.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
        System.out.println(tf.lengthOfLongestSubstringTwoDistinct("121"));
        System.out.println(tf.lengthOfLongestSubstringTwoDistinct("0122"));
        System.out.println(tf.lengthOfLongestSubstringTwoDistinct("12322"));
        System.out.println(tf.lengthOfLongestSubstringTwoDistinct("33312112334"));
    }
}
