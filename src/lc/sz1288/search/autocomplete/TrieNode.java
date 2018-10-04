package lc.sz1288.search.autocomplete;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TrieNode {
    char ch;
    TrieNode[] children;
    int count;

    public static TrieNode fromSentences(String[] sentences, int[] times) {
        TrieNode root = new TrieNode('*');
        for (int i = 0; i < sentences.length; i++) {
            root.addSentence(sentences[i], times[i]);
        }
        return root;
    }

    public void addSentence(String sentence, int times) {
        TrieNode current = this;
        for (int j = 0; j < sentence.length() - 1; j++) {
            char ch = sentence.charAt(j);
            int index = ch == ' ' ? 26 : ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode(ch);
            }
            current = current.children[index];
        }
        char ch = sentence.charAt(sentence.length() - 1);
        int index = ch == ' ' ? 26 : ch - 'a';
        if (current.children[index] == null) {
            current.children[index] = new TrieNode(ch, times);
        } else {
            current.children[index].count += times;
        }
    }

    public TrieNode(char ch) {
        this.ch = ch;
        this.children = new TrieNode[27];
    }

    public TrieNode(char ch, int times) {
        this.ch = ch;
        this.children = new TrieNode[27];
        this.count = times;
    }

    public TrieNode getChild(char ch) {
        return children[ch == ' ' ? 26 : ch - 'a'];
    }

    public List<String> getThreeMostFrequentSentences() {
        PriorityQueue<StringCount> topSentences = new PriorityQueue<>(3);
        recurse(topSentences, "");
        LinkedList<String> res = new LinkedList<>();
        while (!topSentences.isEmpty()) {
            res.push(topSentences.poll().string);
        }
        return res;
    }

    private void recurse(PriorityQueue<StringCount> topSentences, String current) {
        current += ch;
        if (count != 0) {
            StringCount toAdd = new StringCount(current, count);
            if (topSentences.size() < 3) {
                topSentences.add(toAdd);
            } else if (topSentences.peek().compareTo(toAdd) < 0) {
                topSentences.poll();
                topSentences.add(toAdd);
            }
        }
        for (TrieNode child : children) {
            if (child != null) {
                child.recurse(topSentences, current);
            }
        }
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "ch=" + ch +
                ", count=" + count +
                '}';
    }

    private static class StringCount implements Comparable<StringCount> {
        String string;
        int count;

        StringCount(String string, int count) {
            this.string = string;
            this.count = count;
        }

        @Override
        public int compareTo(StringCount o) {
            int diff = count - o.count;
            return diff == 0 ? o.string.compareTo(string) : diff;
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "i love you qingqing"};
        int[] times = {5, 3};
        TrieNode tn = TrieNode.fromSentences(sentences, times);
        System.out.println(tn.getChild('i').getThreeMostFrequentSentences());
    }
}
