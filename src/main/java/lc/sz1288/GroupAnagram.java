package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Given an array of strings, group anagrams together.
 */
public class GroupAnagram {
    public List<List<String>> groupAnagrams_method1(String[] strs) {
        Map<String, List<String>> stringListMap = Stream.of(strs)
                .collect(Collectors.groupingBy(this::sortCharacters));
        return new ArrayList<>(stringListMap.values());
    }

    private String sortCharacters(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<List<Integer>, List<String>> anagrams = Stream.of(strs)
                .collect(Collectors.groupingBy(this::toCharCountList));
        return new ArrayList<>(anagrams.values());
    }

    private List<Integer> toCharCountList(String s) {
        int[] charCount = new int[26];
        for (char ch : s.toCharArray()) {
            charCount[ch - 'a']++;
        }
        return IntStream.of(charCount).boxed().collect(Collectors.toList());
    }
}
