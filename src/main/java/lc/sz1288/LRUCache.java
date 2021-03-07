package lc.sz1288;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * Could you do both operations in O(1) time complexity?
 */
public class LRUCache {
    private final LinkedHashMap<Integer, Integer> linkedHashMap;

    public LRUCache(int capacity) {
        this.linkedHashMap = new CappedLinkedHashMap<>(capacity);
    }

    public int get(int key) {
        Integer value = linkedHashMap.get(key);
        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        linkedHashMap.put(key, value);
    }

    private static class CappedLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public CappedLinkedHashMap(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 2);
        lruCache.put(3, 2);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        lruCache.put(5, 2);
        System.out.println(lruCache.linkedHashMap);
    }
}
