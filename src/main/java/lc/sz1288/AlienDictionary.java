package lc.sz1288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> edges = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        for (String word : words) {
            for (char ch : word.toCharArray()) {
                inDegree.put(ch, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String from = words[i];
            String to = words[i + 1];
            int min = Math.min(from.length(), to.length());
            for (int j = 0; j < min; j++) {
                char small = from.charAt(j);
                char large = to.charAt(j);
                if (small != large) {
                    Set<Character> set = edges.get(small);
                    if (set == null) {
                        set = new HashSet<>();
                    }
                    if (set.add(large)) {
                        inDegree.put(large, inDegree.get(large) + 1);
                    }
                    edges.put(small, set);
                    break;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        while (!queue.isEmpty()) {
            Character ch = queue.poll();
            result.append(ch);
            if (edges.containsKey(ch)) {
                for (char each : edges.get(ch)) {
                    int value = inDegree.get(each) - 1;
                    inDegree.put(each, value);
                    if (value == 0) {
                        queue.add(each);
                    }
                }
            }
        }
        return result.length() == inDegree.size() ? result.toString() : "";
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt", "dsbcjnfksd", "dhjsigfds", "asdasdas"};
        System.out.println(ad.alienOrder(words1));
        String[] words2 = {"z"};
        System.out.println(ad.alienOrder(words2));
    }
}
