package lc.sz1288;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 * <p>
 * Example 1:
 * <p>
 * Input: pattern = "abab", str = "redblueredblue"
 * Output: true
 * Example 2:
 * <p>
 * Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
 * Output: true
 * Example 3:
 * <p>
 * Input: pattern = "aabb", str = "xyzabcxzyabc"
 * Output: false
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        String[] mapping = new String[26];
        Set<String> seen = new HashSet<>();
        boolean[] match = new boolean[1];
        backtrack(match, mapping, seen, pattern, 0, str, 0);
        return match[0];
    }

    private void backtrack(boolean[] match, String[] mapping, Set<String> seen, String pattern, int chIndex, String str, int strIndex) {
        if (match[0]) {
            return;
        }
        if (chIndex == pattern.length() && strIndex == str.length()) {
            match[0] = true;
            return;
        }
        if (chIndex >= pattern.length() || strIndex >= str.length()) {
            return;
        }
        char ch = pattern.charAt(chIndex);
        if (mapping[ch - 'a'] != null) {
            String mappedString = mapping[ch - 'a'];
            if (str.substring(strIndex).startsWith(mappedString)) {
                backtrack(match, mapping, seen, pattern, chIndex + 1, str, strIndex + mappedString.length());
            }
        } else {
            for (int i = str.length(); i >= strIndex + 1; i--) {
                String substring = str.substring(strIndex, i);
                if (seen.add(substring)) {
                    mapping[ch - 'a'] = substring;
                    backtrack(match, mapping, seen, pattern, chIndex + 1, str, i);
                    seen.remove(substring);
                    mapping[ch - 'a'] = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        WordPatternII wpII = new WordPatternII();
        System.out.println(wpII.wordPatternMatch("abab", "redblueredblue"));
        System.out.println(wpII.wordPatternMatch("aaaa", "asdasdasdasd"));
        System.out.println(wpII.wordPatternMatch("aa", "xy"));
    }
}
