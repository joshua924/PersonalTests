package lc.sz1288;

import java.util.HashSet;
import java.util.Set;

public class GetMaxRepetition {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (!contains(s1, s2)) {
            return 0;
        }
        StringBuilder current = new StringBuilder();
        return 0;
    }

    private boolean contains(String s1, String s2) {
        Set<Character> s1Chars = new HashSet<>();
        for (char ch : s1.toCharArray()) {
            s1Chars.add(ch);
        }
        for (char ch : s2.toCharArray()) {
            if (!s1Chars.contains(ch)) {
                return false;
            }
        }
        return true;
    }

    private int consume(String s1, String s2, StringBuilder remain) {
        int cnt = 0;
        int lastMatchIndex = -1;
        int s2Index = 0;
        for (int s1Index = 0; s1Index < s1.length(); s1Index++) {
            if (s1.charAt(s1Index) != s2.charAt(s2Index)) {
                continue;
            }
            if (s2Index == s2.length() - 1) {
                s2Index = 0;
                cnt++;
                lastMatchIndex = s1Index;
            }
        }
        remain.append(s1.substring(lastMatchIndex + 1));
        return cnt;
    }

    public static void main(String[] args) {
        GetMaxRepetition gm = new GetMaxRepetition();
        System.out.println(gm.getMaxRepetitions("abba", 10, "aabb", 2));
    }
}
