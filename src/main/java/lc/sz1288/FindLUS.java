package lc.sz1288;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindLUS {
    public int findLUSlength(String[] strs) {
        Map<Integer, List<String>> map = Stream.of(strs).collect(Collectors.groupingBy(String::length));
        Map<String, Long> countMap = Stream.of(strs).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Set<String> strings = countMap.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toSet());
        for (int length : map.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())) {
            List<String> list = map.get(length);
            for (String s : list) {
                strings.removeIf(sub -> isSubsequent(s, sub));
            }
            list.retainAll(strings);
            Set<String> set = new HashSet<>(list);
            if (list.size() == 1 || set.size() > 1) {
                return length;
            }
        }
        return -1;
    }

    private boolean isSubsequent(String s, String sub) {
        if (s.length() == sub.length()) {
            return false;
        }
        int i = 0, j = 0;
        while (i < s.length() && j < sub.length()) {
            if (s.charAt(i) == sub.charAt(j)) j++;
            i++;
        }
        return j == sub.length();
    }

    public static void main(String[] args) {
        FindLUS fl = new FindLUS();
        System.out.println(fl.findLUSlength(new String[]{"a", "b", "c", "d", "e", "f", "a", "b", "c", "d", "e", "f"}));
        System.out.println(fl.findLUSlength(new String[]{"aba", "aba", "aa"}));
        System.out.println(fl.findLUSlength(new String[]{"aba", "erb", "cac"}));
    }
}
