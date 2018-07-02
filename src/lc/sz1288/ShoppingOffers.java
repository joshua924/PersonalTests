package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingOffers {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return backtrack(price, special, needs, 0);
    }

    private int backtrack(List<Integer> price, List<List<Integer>> specials, List<Integer> needs, int index) {
        int min = directPurchase(price, needs);
        for (int i = index; i < specials.size(); i++) {
            List<Integer> special = specials.get(i);
            List<Integer> remaining = new ArrayList<>();
            for (int item = 0; item < needs.size(); item++) {
                if (needs.get(item) < special.get(item)) {
                    remaining = null;
                    break;
                }
                remaining.add(needs.get(item) - special.get(item));
            }
            if (remaining != null) {
                min = Math.min(min, special.get(special.size() - 1) + backtrack(price, specials, remaining, i));
            }
        }
        return min;
    }

    private int directPurchase(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < needs.size(); i++) {
            total += price.get(i) * needs.get(i);
        }
        return total;
    }

    public static void main(String[] args) {
        ShoppingOffers so = new ShoppingOffers();
        System.out.println(
                so.shoppingOffers(Arrays.asList(9, 9), Arrays.asList(Arrays.asList(1, 1, 1)), Arrays.asList(6, 6))
        );
    }
}
