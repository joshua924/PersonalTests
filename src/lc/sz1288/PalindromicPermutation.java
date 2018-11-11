package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
 * <p>
 * Example 1:
 * <p>
 * Input: "aabb"
 * Output: ["abba", "baab"]
 * Example 2:
 * <p>
 * Input: "abc"
 * Output: []
 */
public class PalindromicPermutation {
    public List<String> generatePalindromes(String s) {
        int[] count = new int[26];
        List<String> res = new ArrayList<>();
        int oddCharCount = 0;
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
            oddCharCount += count[ch - 'a'] % 2 == 0 ? -1 : 1;
        }
        if (oddCharCount > 1) {
            return res;
        }
        String current = "";
        for (char c = 'a'; c < 'z'; c++) {
            if (count[c - 'a'] % 2 == 1) {
                current += c;
                break;
            }
        }
        backtrack(res, count, current, s.length());
        return res;
    }

    private void backtrack(List<String> res, int[] count, String current, int length) {
        if (current.length() == length) {
            res.add(current);
            return;
        }
        for (char c = 'a'; c < 'z'; c++) {
            if (count[c - 'a'] >= 2) {
                count[c - 'a'] -= 2;
                String next = c + current + c;
                backtrack(res, count, next, length);
                count[c - 'a'] += 2;
            }
        }
    }

    public static void main(String[] args) {
        PalindromicPermutation pp = new PalindromicPermutation();
        System.out.println(pp.generatePalindromes("aabb"));
    }
}
