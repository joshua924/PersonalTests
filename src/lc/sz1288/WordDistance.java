package lc.sz1288;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * <p>
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 */
public class WordDistance {
    private final Map<String, List<Integer>> positions;

    public WordDistance(String[] words) {
        positions = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            List<Integer> list = positions.getOrDefault(word, new ArrayList<>());
            list.add(i);
            positions.put(word, list);
        }
    }

    public int shortest(String word1, String word2) {
        return shortest(positions.get(word1), positions.get(word2));
    }

    public int shortestWordDistance(String[] words, String word1, String word2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                list1.add(i);
            }
            if (words[i].equals(word2)) {
                list2.add(i);
            }
        }
        return shortest(list1, list2);
    }

    private int shortest(List<Integer> list1, List<Integer> list2) {
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
            int index1 = list1.get(i);
            int index2 = list2.get(j);
            if (index1 == index2) {
                i++;
                continue;
            }
            min = Math.min(min, Math.abs(index1 - index2));
            if (index1 < index2) {
                i++;
            } else {
                j++;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance swd = new WordDistance(words);
        System.out.println(swd.shortest("makes", "makes"));
    }
}
