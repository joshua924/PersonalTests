package lc.sz1288.search.autocomplete;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to
 * return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
 * <p>
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one
 * appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 * <p>
 * The constructor function:
 * <p>
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the
 * corresponding times a sentence has been typed. Your system should record these historical data.
 * <p>
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 * <p>
 * List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also,
 * the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 * <p>
 * <p>
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 * <p>
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should
 * be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * <p>
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 * <p>
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 * <p>
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 */
public class AutocompleteSystem {
    private TrieNode trie;
    private TrieNode current;
    private String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.trie = TrieNode.fromSentences(sentences, times);
        this.current = trie;
        this.prefix = "";
    }

    public List<String> input(char c) {
        switch (c) {
            case '#':
                trie.addSentence(prefix, 1);
                current = trie;
                prefix = "";
                return Collections.emptyList();
            default:
                prefix += c;
                if (current == null) {
                    return Collections.emptyList();
                }
                current = current.getChild(c);
                if (current == null) {
                    return Collections.emptyList();
                }
                return current.getThreeMostFrequentSentences().stream()
                        .map(s -> prefix + s.substring(1))
                        .collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        AutocompleteSystem acs = new AutocompleteSystem(sentences, times);
        System.out.println(acs.input('i'));
        System.out.println(acs.input(' '));
        System.out.println(acs.input('a'));
        System.out.println(acs.input('#'));
        System.out.println(acs.input('i'));
        System.out.println(acs.input(' '));
        System.out.println(acs.input('a'));
    }
}
