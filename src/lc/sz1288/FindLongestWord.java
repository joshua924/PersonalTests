package lc.sz1288;

import java.util.Arrays;
import java.util.List;

public class FindLongestWord {
    public String findLongestWord(String s, List<String> dict) {
        String longest = "";
        for (String word : dict) {
            if (isSubsequent(s, word)) {
                if (longest.length() < word.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }
        return longest;
    }

    private boolean isSubsequent(String s, String sub) {
        int i = 0, j = 0;
        while (i < s.length() && j < sub.length()) {
            if (s.charAt(i) == sub.charAt(j)) j++;
            i++;
        }
        return j == sub.length();
    }

    public static void main(String[] args) {
        FindLongestWord fl = new FindLongestWord();
        System.out.println(fl.findLongestWord("abpcplea", Arrays.asList("ale", "apple", "monkey", "plea")));
        System.out.println(fl.findLongestWord("abpcplea", Arrays.asList("abcea", "apple", "monkey", "plea")));
    }
}
