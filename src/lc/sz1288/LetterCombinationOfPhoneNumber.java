package lc.sz1288;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 */
public class LetterCombinationOfPhoneNumber {
    private static final String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<>();
        if (digits.isEmpty()) {
            return ans;
        }
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.poll();
                for (char s : mapping[x].toCharArray())
                    ans.offer(t + s);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LetterCombinationOfPhoneNumber lcopn = new LetterCombinationOfPhoneNumber();
        System.out.println(lcopn.letterCombinations("23"));
    }
}
