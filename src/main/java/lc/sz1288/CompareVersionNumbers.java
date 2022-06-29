package lc.sz1288;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given two version numbers, version1 and version2, compare them.
 * <p>
 * Version numbers consist of one or more revisions joined by a dot '.'.
 * Each revision consists of digits and may contain leading zeros.
 * Every revision contains at least one character.
 * Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on.
 * For example 2.5.33 and 0.1 are valid version numbers.
 * <p>
 * To compare version numbers, compare their revisions in left-to-right order.
 * Revisions are compared using their integer value ignoring any leading zeros.
 * This means that revisions 1 and 001 are considered equal.
 * If a version number does not specify a revision at an index, then treat the revision as 0.
 * For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        List<Integer> ver1 = Stream.of(version1.split("\\.")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> ver2 = Stream.of(version2.split("\\.")).map(Integer::parseInt).collect(Collectors.toList());

        int minLength = Math.min(ver1.size(), ver2.size());
        int maxLength = Math.max(ver1.size(), ver2.size());
        for (int i = 0; i < minLength; i++) {
            int res = Integer.compare(ver1.get(i), ver2.get(i));
            if (res < 0) {
                return -1;
            } else if (res > 0) {
                return 1;
            }
        }
        for (int i = minLength; i < maxLength; i++) {
            if (i < ver1.size() && ver1.get(i) > 0) {
                return 1;
            }
            if (i < ver2.size() && ver2.get(i) > 0) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersionNumbers solution = new CompareVersionNumbers();
        System.out.println(solution.compareVersion("1.01", "1.001"));
        System.out.println(solution.compareVersion("1.0", "1.0.0"));
        System.out.println(solution.compareVersion("1.0", "2.0.1"));
        System.out.println(solution.compareVersion("1.0.0", "1.0.0.1"));
    }
}
