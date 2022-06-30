package lc.sz1288;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        Map<Character, Integer> firstIndex = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (firstIndex.containsKey(ch)) {
                firstIndex.replace(ch, Integer.MAX_VALUE);
            } else {
                firstIndex.put(ch, i);
            }
        }
        return firstIndex.values().stream().filter(i -> i != Integer.MAX_VALUE)
                .findFirst().orElse(-1);
    }

    public static void main(String[] args) {
        FirstUniqueCharacter solution = new FirstUniqueCharacter();
        System.out.println(solution.firstUniqChar("leetcode"));
        System.out.println(solution.firstUniqChar("loveleetcode"));
        System.out.println(solution.firstUniqChar("aadadaad"));
    }
}
