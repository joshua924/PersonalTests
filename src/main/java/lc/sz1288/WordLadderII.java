package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord,
 * or an empty list if no such sequence exists.
 *
 * Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 */
public class WordLadderII {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    dict.remove(beginWord);

    // this map contains all words that can reach the key
    Map<String, Set<String>> reachedWithPrevStep = new HashMap<>();
    reachedWithPrevStep.put(beginWord, new HashSet<>());

    Set<String> currentLevel = new HashSet<>();
    currentLevel.add(beginWord);

    while (!reachedWithPrevStep.containsKey(endWord)) {
      Set<String> nextLevel = new HashSet<>();
      for (String each : currentLevel) {
        for (int i = 0; i < each.length(); i++) {
          char[] chars = each.toCharArray();
          for (char ch = 'a'; ch <= 'z'; ch++) {
            chars[i] = ch;
            String word = new String(chars);
            if (dict.contains(word)) {
              nextLevel.add(word);
              Set<String> prevSteps = reachedWithPrevStep.getOrDefault(word, new HashSet<>());
              prevSteps.add(each);
              reachedWithPrevStep.put(word, prevSteps);
            }
          }
        }
      }
      if (nextLevel.isEmpty()) {
        return new ArrayList<>();
      }
      dict.removeAll(nextLevel);
      currentLevel = nextLevel;
    }

    List<List<String>> result = new ArrayList<>();

    LinkedList<String> initialPath = new LinkedList<>();
    initialPath.add(endWord);
    dfs(reachedWithPrevStep, endWord, beginWord, initialPath, result);

    return result;
  }

  private void dfs(Map<String, Set<String>> edges, String current, String target,
                   LinkedList<String> path, List<List<String>> result) {
    if (current.equals(target)) {
      result.add(new ArrayList<>(path));
      return;
    }

    for (String next : edges.get(current)) {
      path.addFirst(next);
      dfs(edges, next, target, path, result);
      path.removeFirst();
    }
  }

  public static void main(String[] args) {
    WordLadderII wl = new WordLadderII();
    System.out.println(wl.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    System.out.println(wl.findLadders("a", "c", Arrays.asList("a", "b", "c")));
  }
}
