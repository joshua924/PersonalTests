package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Example 1:
 * <p>
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * Example 2:
 * <p>
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * Example 3:
 * <p>
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * Example 4:
 * <p>
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String[] tokens = str.split(" ");
        if (tokens.length != pattern.length()) {
            return false;
        }
        Map<Character, String> mapping = new HashMap<>();
        Map<String, Character> reverseMapping = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String token = tokens[i];
            String existingS = mapping.put(ch, token);
            Character existingC = reverseMapping.put(token, ch);
            if (existingS != null && !existingS.equals(token) || existingC != null && existingC != ch) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern wp = new WordPattern();
        System.out.println(wp.wordPattern("abba", "dog dog dog dog"));
//        System.out.println(wp.wordPattern("abba", "dog cat cat fish"));
//        System.out.println(wp.wordPattern("abbd", "dog cat cat fish"));
//        System.out.println(wp.wordPattern("a", ""));
    }
}
