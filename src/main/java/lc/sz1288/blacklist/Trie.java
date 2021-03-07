package lc.sz1288.blacklist;

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

    public boolean contains(String s) {
        Trie t = this;
        for (int i = 0; i < s.length(); i++) {
            int index = toIndex(s.charAt(i));
            if (t.branches[index] == null) {
                return false;
            }
            t = t.branches[index];
            if (t.times > 0 && (i == s.length() - 1 || s.charAt(i + 1) == ' ')) {
                return true;
            }
        }
        return false;
    }

    private int toIndex(char c) {
        return c == ' ' ? 26 : c - 'a';
    }
}
