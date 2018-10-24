package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> count = new HashMap<>();
        for (char ch : t.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int left = 0;
        int match = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (count.containsKey(ch)) {
                count.put(ch, count.get(ch) - 1);
                if (count.get(ch) >= 0) {
                    match++;
                }
            }
            while (match == t.length()) {
                if (right - left + 1 < minLen) {
                    minStart = left;
                    minLen = right - left + 1;
                }
                char leftCh = s.charAt(left);
                if (count.containsKey(leftCh)) {
                    count.put(leftCh, count.get(leftCh) + 1);
                    if (count.get(leftCh) > 0) {
                        match--;
                    }
                }
                left++;
            }
        }
        return s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.println(mws.minWindow("ADOBECODEBANC", "ABC"));
    }
}
