package lc.sz1288;

import java.util.HashSet;
import java.util.Set;

public class LongestNonRepeatingSubstring {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> chars = new HashSet<>();
        int max = 0, tail = 0;

        for (int head = 0; head < s.length(); head++) {
            char current = s.charAt(head);
            if (!chars.contains(current)) {
                chars.add(current);
                max = Math.max(head - tail + 1, max);
            } else {
                while (s.charAt(tail) != current) {
                    chars.remove(s.charAt(tail++));
                }
                tail++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestNonRepeatingSubstring solution = new LongestNonRepeatingSubstring();
        System.out.println(solution.lengthOfLongestSubstring("tmmzuxt"));
        System.out.println(solution.lengthOfLongestSubstring(""));
        System.out.println(solution.lengthOfLongestSubstring("a"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
