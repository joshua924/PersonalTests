package lc.sz1288;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Given an array of strings, group anagrams together.
 */
public class GroupAnagram {
    /**
     * this solution converts each string to a hashed representation, reducing the size of the key for each anagram
     * group. Time complexity is O(nm) with n being the number of strings and m being average length of strings.
     * <p>
     * Other solutions such as sorting each string to form the key would bring up the time complexity to O(nmlog(m)).
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = Stream.of(strs)
                .collect(Collectors.groupingBy(this::toCharCountHash));
        return new ArrayList<>(anagrams.values());
    }

    private String toCharCountHash(String s) {
        int[] charCount = new int[26];
        for (char ch : s.toCharArray()) {
            charCount[ch - 'a']++;
        }
        return IntStream.of(charCount).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        GroupAnagram solution = new GroupAnagram();
        System.out.println(solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
