package lc.sz1288;

import java.util.Arrays;

/**
 Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 */
public class LongestKRepeatSubstring {
  public int longestSubstring(String s, int k) {
    char[] str = s.toCharArray();
    int[] countMap = new int[26];
    int maxUnique = getMaxUniqueLetters(s);
    int result = 0;

    for (int currUnique = 1; currUnique <= maxUnique; currUnique++) {
      Arrays.fill(countMap, 0);
      int tail = 0, head = 0, idx, unique = 0, countAtLeastK = 0;
      while (head < str.length) {
        if (unique <= currUnique) {
          idx = str[head] - 'a';
          if (countMap[idx] == 0) {
            unique++;
          }
          countMap[idx]++;
          if (countMap[idx] == k) {
            countAtLeastK++;
          }
          head++;
        } else {
          idx = str[tail] - 'a';
          if (countMap[idx] == k) {
            countAtLeastK--;
          }
          countMap[idx]--;
          if (countMap[idx] == 0) {
            unique--;
          }
          tail++;
        }
        if (unique == currUnique && unique == countAtLeastK) {
          result = Integer.max(result, head - tail);
        }
      }
    }

    return result;
  }

  private int getMaxUniqueLetters(String s) {
    boolean[] map = new boolean[26];
    int maxUnique = 0;
    for (int i = 0; i < s.length(); i++) {
      if (!map[s.charAt(i) - 'a']) {
        maxUnique++;
        map[s.charAt(i) - 'a'] = true;
      }
    }
    return maxUnique;
  }

  public static void main(String[] args) {
    LongestKRepeatSubstring solution = new LongestKRepeatSubstring();
    System.out.println(solution.longestSubstring("aabbac", 2));
  }
}
