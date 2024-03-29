package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 * A substring is a contiguous sequence of characters within the string.
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> count = new HashMap<>();
        for (char ch : t.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int minStart = 0;
        int match = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Integer currentCount = count.get(ch);
            if (currentCount != null) {
                count.put(ch, currentCount - 1);
                if (currentCount >= 1) {
                    match++;
                }
            }
            while (match == t.length()) {
                if (i - start + 1 < minLen) {
                    minStart = start;
                    minLen = i - start + 1;
                }
                char left = s.charAt(start);
                Integer current = count.get(left);
                if (current != null) {
                    count.put(left, current + 1);
                    if (current >= 0) {
                        match--;
                    }
                }
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.println(mws.minWindow("ADOBECODEBANC", "ABC"));
    }
}
