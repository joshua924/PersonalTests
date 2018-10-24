package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 * <p>
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        Set<List<Integer>> res = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        if (map.containsKey("")) {
            int index = map.get("");
            for (int i = 0; i < words.length; i++) {
                if (i != index && isPalindrome(words[i])) {
                    res.add(Arrays.asList(i, index));
                    res.add(Arrays.asList(index, i));
                }
            }
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder sb = new StringBuilder(word).reverse();
            for (int j = sb.length(); j >= 0; j--) {
                if (isPalindrome(sb.substring(j))) {
                    String prefix = sb.substring(0, j);
                    if (!prefix.equals(word) && map.containsKey(prefix)) {
                        res.add(Arrays.asList(map.get(prefix), i));
                    }
                }
            }
            for (int j = 0; j < sb.length(); j++) {
                if (isPalindrome(sb.substring(0, j))) {
                    String suffix = sb.substring(j);
                    if (!suffix.equals(word) && map.containsKey(suffix)) {
                        res.add(Arrays.asList(i, map.get(suffix)));
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    private boolean isPalindrome(String s) {
        int length = s.length();
        for (int i = 0; i < length / 2; i++) {
            if (s.charAt(i) != s.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();
        System.out.println(pp.palindromePairs(new String[]{"a", "ab", "ba", "", "abc"}));
    }
}
