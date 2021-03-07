package lc.sz1288;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Set<String> reached = new HashSet<>();
        reached.add(beginWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (dict.remove(word)) {
                            toAdd.add(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.isEmpty()) {
                return 0;
            }
            reached = toAdd;
        }
        return distance;
    }

    public static void main(String[] args) {
        WordLadder wl = new WordLadder();
        System.out.println(wl.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }
}
