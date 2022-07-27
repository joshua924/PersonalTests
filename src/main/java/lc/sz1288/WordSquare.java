package lc.sz1288;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times.
 * You can return the answer in any order.
 *
 * A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 */
public class WordSquare {
  public List<List<String>> wordSquares(String[] words) {
    Map<String, List<String>> prefixes = new HashMap<>();
    int length = words[0].length();
    for (String word : words) {
      for (int i = 1; i <= length; i++) {
        String pre = word.substring(0, i);
        if (!prefixes.containsKey(pre)) {
          prefixes.put(pre, new LinkedList<>());
        }
        prefixes.get(pre).add(word);
      }
    }
    List<List<String>> result = new LinkedList<>();
    for (String word : words) {
      LinkedList<String> current = new LinkedList<>();
      current.add(word);
      findSquare(1, length, current, prefixes, result);
    }
    return result;
  }

  private void findSquare(int step,
                          int target,
                          LinkedList<String> current,
                          Map<String, List<String>> prefixes,
                          List<List<String>> result) {
    if (step == target) {
      result.add(new ArrayList<>(current));
      return;
    }
    StringBuilder prefix = new StringBuilder();
    for (String each : current) {
      prefix.append(each.charAt(step));
    }
    for (String next : prefixes.getOrDefault(prefix.toString(), new ArrayList<>())) {
      current.addLast(next);
      findSquare(step + 1, target, current, prefixes, result);
      current.removeLast();
    }
  }

  public static void main(String[] args) {
    WordSquare solution = new WordSquare();
    String[] words = {"area", "lead", "wall", "lady", "ball"};
    System.out.println(solution.wordSquares(words));
  }
}
