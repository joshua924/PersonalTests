package lc.sz1288;

import java.util.*;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * 1. Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * 2. Find all strobogrammatic numbers that are of length = n.
 */
public class StrobogrammaticNumber {
    private static final Map<Character, Character> MAPPINGS = new HashMap<>();

    static {
        MAPPINGS.put('0', '0');
        MAPPINGS.put('1', '1');
        MAPPINGS.put('6', '9');
        MAPPINGS.put('8', '8');
        MAPPINGS.put('9', '6');
    }

    public int strobogrammaticInRange(String low, String high) {
        List<String> allNumbers = new ArrayList<>();
        for (int len = low.length(); len <= high.length(); len++) {
            allNumbers.addAll(findStrobogrammatic(len));
        }
        long min = Long.parseLong(low);
        long max = Long.parseLong(high);
        allNumbers.removeIf(s -> {
            long anInt = Long.parseLong(s);
            return anInt < min || anInt > max;
        });
        return allNumbers.size();
    }

    public boolean isStrobogrammatic(String num) {
        for (int idx = 0; idx <= num.length() - 1 - idx; idx++) {
            char charAtIdx = num.charAt(idx);
            if (!MAPPINGS.containsKey(charAtIdx) || MAPPINGS.get(charAtIdx) != num.charAt(num.length() - 1 - idx)) {
                return false;
            }
        }
        return true;
    }

    public List<String> findStrobogrammatic(int n) {
        return recurse(n, n);
    }

    private List<String> recurse(int n, int m) {
        if (n == 0) {
            return new ArrayList<>(Collections.singletonList(""));
        }
        if (n == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        }
        List<String> list = recurse(n - 2, m);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (n != m) {
                res.add("0" + s + "0");
            }
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }
        return res;
    }

    public static void main(String[] args) {
        StrobogrammaticNumber sn = new StrobogrammaticNumber();
        System.out.println(sn.isStrobogrammatic("797"));
        System.out.println(sn.findStrobogrammatic(4));
        System.out.println(sn.findStrobogrammatic(5));
        System.out.println(sn.strobogrammaticInRange("0", "2147483647"));
    }
}
