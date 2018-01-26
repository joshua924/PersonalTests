package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> res = new ArrayList<>();
        Set<String> atomicWords = new HashSet<>();
        for (String word : words) {
            if (canBeConcatenated(word, atomicWords)) {
                res.add(word);
            } else {
                atomicWords.add(word);
            }
        }
        return res;
    }

    private boolean canBeConcatenated(String word, Set<String> seenWords) {
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && seenWords.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

    public static void main(String[] args) {
        ConcatenatedWords cw = new ConcatenatedWords();
        String[] words = {"", "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        System.out.println(cw.findAllConcatenatedWordsInADict(words));
    }
}
