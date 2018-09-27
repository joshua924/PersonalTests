package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Write a function to generate the generalized abbreviations of a word.
 * <p>
 * Note: The order of the output does not matter.
 * <p>
 * Example:
 * <p>
 * Input: "word"
 * Output:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        if (word.isEmpty()) {
            return Collections.singletonList("");
        }
        List<String> res = new ArrayList<>();
        backtrack(res, word, 0, 0, new StringBuilder());
        return res;
    }

    private void backtrack(List<String> res, String word, int index, int currentLength, StringBuilder current) {
        if (index == word.length()) {
            if (currentLength > 0) {
                current.append(currentLength);
            }
            res.add(current.toString());
            return;
        }
        backtrack(res, word, index + 1, currentLength + 1, new StringBuilder(current));
        if (currentLength > 0) {
            current.append(currentLength);
        }
        backtrack(res, word, index + 1, 0, current.append(word.charAt(index)));
    }

    public static void main(String[] args) {
        GeneralizedAbbreviation ga = new GeneralizedAbbreviation();
        System.out.println(ga.generateAbbreviations(""));
        System.out.println(ga.generateAbbreviations("a"));
        System.out.println(ga.generateAbbreviations("word"));
    }
}
