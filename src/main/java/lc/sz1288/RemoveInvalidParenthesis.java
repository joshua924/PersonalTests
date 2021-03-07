package lc.sz1288;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveInvalidParenthesis {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> moves = new HashSet<>();
        backtrack(moves, s, "", 0, 0);
        Map<Integer, List<String>> lengthMap = moves.stream().collect(Collectors.groupingBy(String::length));
        return lengthMap.get(Collections.max(lengthMap.keySet()));
    }

    private void backtrack(Set<String> moves, String s, String current, int leftCount, int index) {
        if (index == s.length()) {
            if (leftCount == 0) {
                moves.add(current);
            }
            return;
        }
        char ch = s.charAt(index);
        switch (ch) {
            case '(':
                backtrack(moves, s, current, leftCount, index + 1);
                backtrack(moves, s, current + ch, leftCount + 1, index + 1);
                break;
            case ')':
                backtrack(moves, s, current, leftCount, index + 1);
                if (leftCount > 0) {
                    backtrack(moves, s, current + ch, leftCount - 1, index + 1);
                }
                break;
            default:
                backtrack(moves, s, current + ch, leftCount, index + 1);
        }
    }

    public static void main(String[] args) {
        RemoveInvalidParenthesis rip = new RemoveInvalidParenthesis();
        System.out.println(rip.removeInvalidParentheses("(()"));
        System.out.println(rip.removeInvalidParentheses("(a)())()"));
        System.out.println(rip.removeInvalidParentheses("()())()"));
        System.out.println(rip.removeInvalidParentheses(")("));
    }
}
