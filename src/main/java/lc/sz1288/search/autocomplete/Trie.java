package lc.sz1288.search.autocomplete;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private int times;
    private Trie[] branches = new Trie[27];

    public void insert(String s, int times) {
        Trie t = this;
        for (int i = 0; i < s.length(); i++) {
            int index = toIndex(s.charAt(i));
            if (t.branches[index] == null) {
                t.branches[index] = new Trie();
            }
            t = t.branches[index];
        }
        t.times += times;
    }

    public List<Node> findWithPrefix(String prefix) {
        Trie t = this;
        List<Node> res = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            int index = toIndex(prefix.charAt(i));
            if (t.branches[index] == null) {
                return new ArrayList<>();
            }
            t = t.branches[index];
        }
        traverse(prefix, t, res);
        return res;
    }

    private void traverse(String s, Trie t, List<Node> list) {
        if (t.times > 0) {
            list.add(new Node(s, t.times));
        }
        for (char i = 'a'; i <= 'z'; i++) {
            if (t.branches[i - 'a'] != null) {
                traverse(s + i, t.branches[i - 'a'], list);
            }
        }
        if (t.branches[26] != null) {
            traverse(s + ' ', t.branches[26], list);
        }
    }

    private int toIndex(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    public static class Node {
        String sentence;
        int frequency;

        public Node(String sentence, int frequency) {
            this.sentence = sentence;
            this.frequency = frequency;
        }
    }
}
