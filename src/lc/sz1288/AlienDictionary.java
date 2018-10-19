package lc.sz1288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> edges = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        String result = "";
        if (words == null || words.length == 0) {
            return result;
        }
        for (String s : words) {
            for (char c : s.toCharArray()) {
                inDegree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = edges.getOrDefault(c1, new HashSet<>());
                    if (set.add(c2)) {
                        edges.put(c1, set);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                q.add(c);
            }
        }
        while (!q.isEmpty()) {
            char ch = q.remove();
            result += ch;
            if (edges.containsKey(ch)) {
                for (char c2 : edges.get(ch)) {
                    inDegree.put(c2, inDegree.get(c2) - 1);
                    if (inDegree.get(c2) == 0) {
                        q.add(c2);
                    }
                }
            }
        }
        if (result.length() != inDegree.size()) {
            return "";
        }
        return result;
    }

    public static void main(String[] args) {
        AlienDictionary ad = new AlienDictionary();
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt", "dsbcjnfksd", "dhjsigfds", "asdasdas"};
        System.out.println(ad.alienOrder(words1));
        String[] words2 = {"z"};
        System.out.println(ad.alienOrder(words2));
    }
}
