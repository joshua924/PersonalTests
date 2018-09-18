package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a MapSum class with insert, and sum methods.
 * <p>
 * For the method insert, you'll be given a pair of (string, integer).
 * The string represents the key and the integer represents the value.
 * If the key already existed, then the original key-value pair will be overridden to the new one.
 * <p>
 * For the method sum, you'll be given a string representing the prefix,
 * and you need to return the sum of all the pairs' value whose key starts with the prefix.
 */
public class MapSum {
    private Map<String, Integer> words;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        words = new HashMap<>();
    }

    public void insert(String key, int val) {
        words.put(key, val);
    }

    public int sum(String prefix) {
        return words.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(prefix))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
