package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FindRestaurant {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> res = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            String s = list1[i];
            res.put(s, i);
        }
        TreeMap<Integer, List<String>> common = new TreeMap<>();
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            if (res.containsKey(s)) {
                int key = res.get(s) + i;
                if (common.containsKey(key)) {
                    common.get(key).add(s);
                } else {
                    common.put(key, new ArrayList<>(Collections.singletonList(s)));
                }
            }
        }
        return common.firstEntry().getValue().toArray(new String[0]);
    }

    public static void main(String[] args) {
        FindRestaurant fr = new FindRestaurant();
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        String[] list3 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list4 = {"Tapioca Express", "Shogun", "Burger King", "KFC"};
        System.out.println(Arrays.toString(fr.findRestaurant(list1, list2)));
        System.out.println(Arrays.toString(fr.findRestaurant(list3, list4)));
    }
}
