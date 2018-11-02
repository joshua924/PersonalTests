package lc.sz1288.blacklist;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a black list of words and a list of sentences, decide which ones contain a bad word.
 */
public class BlackList {
    public List<Boolean> containBlackListWord(List<String> sentences, List<String> blackListWords) {
        Trie root = new Trie();
        for (String word : blackListWords) {
            root.insert(word, 1);
        }
        return sentences.stream()
                .map(s -> isInTrie(s.toLowerCase(), root))
                .collect(Collectors.toList());
    }

    private boolean isInTrie(String s, Trie root) {
        while (s.contains(" ")) {
            if (root.contains(s)) {
                return true;
            }
            s = s.substring(s.indexOf(" ") + 1);
        }
        return root.contains(s);
    }

    public static void main(String[] args) {
        BlackList bl = new BlackList();
        List<String> blackListWords = Arrays.asList("porn", "world war i", "china");
        List<String> sentences = Arrays.asList(
                "I love porn",
                "I love world war i",
                "world war i is awesome",
                "I love world war ii",
                "I am from china",
                "I live in us");
        System.out.println(bl.containBlackListWord(sentences, blackListWords));
    }
}
