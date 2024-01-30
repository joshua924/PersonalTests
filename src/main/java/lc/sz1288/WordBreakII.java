package lc.sz1288;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreakII {
  public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    List<List<String>> result = new ArrayList<>();
    dfs(s, 0, dict, result, new ArrayList<>());
    return result
        .stream()
        .map(list -> String.join(" ", list))
        .collect(Collectors.toList());
  }

  private void dfs(String s, int index, Set<String> dict, List<List<String>> result,
                   List<String> current) {
    if (index == s.length()) {
      result.add(new ArrayList<>(current));
      return;
    }
    for (int end = index + 1; end <= s.length(); end++) {
      String substring = s.substring(index, end);
      if (dict.contains(substring)) {
        current.add(substring);
        dfs(s, end, dict, result, current);
        current.remove(current.size() - 1);
      }
    }
  }

  public static void main(String[] args) {
    WordBreakII solution = new WordBreakII();
    System.out.println(solution.wordBreak(
        "pineapplepenapple",
        Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")
    ));
  }
}
