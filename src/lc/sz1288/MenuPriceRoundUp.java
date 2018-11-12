package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a menu with a list of prices representing different items, and a target price K,
 * find all the combination of items that can sum up to that amount.
 */
public class MenuPriceRoundUp {
    private static final double DIFF = 0.0001;

    public List<List<Integer>> roundMenu(double[] prices, double K) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, prices, 0, new ArrayList<>(), 0, K);
        return res;
    }

    private void backtrack(List<List<Integer>> res, double[] prices, int index, List<Integer> current, double sum, double k) {
        if (Math.abs(sum - k) <= DIFF) {
            res.add(new ArrayList<>(current));
            return;
        }
        if (sum > k || index >= prices.length) {
            return;
        }
        backtrack(res, prices, index + 1, current, sum, k);
        current.add(index);
        backtrack(res, prices, index + 1, current, sum + prices[index], k);
        current.remove(current.size() - 1);
    }

    public static void main(String[] args) {
        MenuPriceRoundUp mp = new MenuPriceRoundUp();
        double[] prices = new double[]{1.0, 2.5, 2.50, 3.0, 3.5, 4.0, 4.5, 5.0, 5.00000001};
        System.out.println(mp.roundMenu(prices, 10));
    }
}
