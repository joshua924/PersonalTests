package lc.sz1288;

import java.util.*;

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
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = word.length(); j >= 0; j--) {
                if (isPalindrome(word.substring(j))) {
                    String suffix = new StringBuilder(word.substring(0, j)).reverse().toString();
                    if (map.containsKey(suffix) && !suffix.equals(word)) {
                        res.add(Arrays.asList(i, map.get(suffix)));
                    }
                }
            }
            for (int j = 0; j <= word.length(); j++) {
                if (isPalindrome(word.substring(0, j))) {
                    String prefix = new StringBuilder(word.substring(j)).reverse().toString();
                    if (map.containsKey(prefix) && !prefix.equals(word)) {
                        res.add(Arrays.asList(map.get(prefix), i));
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
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
