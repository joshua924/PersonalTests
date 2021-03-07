package lc.sz1288;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
 * For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */
public class LFUCache {
    private final Map<Integer, Integer> map;
    private final Map<Integer, Integer> frequencyMap;
    private final TreeMap<Integer, LinkedHashSet<Integer>> invertedFrequencyMap;
    private final int capacity;

    public LFUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.frequencyMap = new HashMap<>();
        this.invertedFrequencyMap = new TreeMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            addFrequency(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.replace(key, value);
            addFrequency(key);
        } else if (map.size() < capacity) {
            addKey(key, value);
        } else {
            if (capacity == 0) {
                return;
            }
            LinkedHashSet<Integer> leastFrequentKeys = invertedFrequencyMap.firstEntry().getValue();
            Integer toRemove = leastFrequentKeys.iterator().next();
            map.remove(toRemove);
            frequencyMap.remove(toRemove);
            leastFrequentKeys.remove(toRemove);
            if (leastFrequentKeys.isEmpty()) {
                invertedFrequencyMap.pollFirstEntry();
            }
            addKey(key, value);
        }
    }

    private void addFrequency(int key) {
        Integer frequency = frequencyMap.get(key);
        frequencyMap.put(key, frequency + 1);
        LinkedHashSet<Integer> set = invertedFrequencyMap.get(frequency);
        set.remove(key);
        if (set.isEmpty()) {
            invertedFrequencyMap.remove(frequency);
        }
        invertedFrequencyMap.computeIfAbsent(frequency + 1, i -> new LinkedHashSet<>()).add(key);
    }

    private void addKey(int key, int value) {
        map.put(key, value);
        frequencyMap.put(key, 1);
        invertedFrequencyMap.computeIfAbsent(1, i -> new LinkedHashSet<>()).add(key);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        System.out.println(cache.invertedFrequencyMap);
        System.out.println(cache.map);
    }
}
