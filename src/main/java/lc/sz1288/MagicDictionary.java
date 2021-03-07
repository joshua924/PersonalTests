package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implement a magic directory with buildDict, and search methods.
 * <p>
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 * <p>
 * For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word,
 * the modified word is in the dictionary you just built.
 */
public class MagicDictionary {
    private List<String> words;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
        words = new ArrayList<>();
    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        words.addAll(Arrays.asList(dict));
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        return words.stream().anyMatch(self -> oneCharacterAway(self, word));
    }

    private boolean oneCharacterAway(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int diffCount = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) {
                return false;
            }
        }
        return diffCount == 1;
    }
}
