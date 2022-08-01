package lc.sz1288;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Trie {
  final Trie[] next;
  String word;
  int count;

  public Trie() {
    this.next = new Trie[26];
    this.count = 0;
  }

  public void insert(String word) {
    Trie current = this;
    for (char c : word.toCharArray()) {
      int i = c - 'a';
      if (current.next[i] == null) {
        current.next[i] = new Trie();
      }
      current = current.next[i];
    }
    current.word = word;
    current.count++;
  }

  public boolean search(String word) {
    Trie current = this;
    for (char c : word.toCharArray()) {
      int i = c - 'a';
      current = current.next[i];
      if (current == null) {
        return false;
      }
    }
    return word.equals(current.word);
  }

  public int count(String word) {
    Trie current = this;
    for (char c : word.toCharArray()) {
      int i = c - 'a';
      current = current.next[i];
      if (current == null) {
        return 0;
      }
    }
    return current.count;
  }

  public boolean startsWith(String prefix) {
    Trie current = this;
    for (char c : prefix.toCharArray()) {
      int i = c - 'a';
      current = current.next[i];
      if (current == null) {
        return false;
      }
    }
    return true;
  }

  public List<String> top5Frequent(String prefix) {
    Trie current = this;
    for (char c : prefix.toCharArray()) {
      int i = c - 'a';
      current = current.next[i];
      if (current == null) {
        return new ArrayList<>();
      }
    }
    Map<String, Integer> counts = new HashMap<>();
    dfs(current, counts);
    return counts
        .keySet()
        .stream()
        .sorted(Comparator
            .comparingInt(counts::get)
            .reversed())
        .limit(5)
        .collect(Collectors.toList());
  }

  public static Trie buildTrie(String[] words) {
    Trie root = new Trie();
    for (String w : words) {
      root.insert(w);
    }
    return root;
  }

  private void dfs(Trie current, Map<String, Integer> counts) {
    if (current.word != null) {
      counts.put(current.word, current.count);
    }
    for (Trie child : current.next) {
      if (child != null) {
        dfs(child, counts);
      }
    }
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("apple");
    System.out.println(trie.count("apple"));
    trie.insert("apple");
    System.out.println(trie.count("apple"));
    System.out.println(trie.search("app"));
    System.out.println(trie.startsWith("app"));
    trie.insert("app");
    System.out.println(trie.search("app"));
    trie.insert("application");
    trie.insert("application");
    trie.insert("application");
    trie.insert("application");
    trie.insert("application");
    trie.insert("appliance");
    trie.insert("appliance");
    trie.insert("appliance");
    trie.insert("apollo");
    trie.insert("aptive");
    trie.insert("aptive");
    System.out.println(trie.top5Frequent("ap"));
    System.out.println(trie.top5Frequent("apt"));
  }
}
