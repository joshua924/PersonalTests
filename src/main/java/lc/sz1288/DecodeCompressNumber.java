package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a compressed string of numbers, return all the possible inputs that can be compressed into it.
 * e.g. if string is 1211, input can be "21" or 121 repetitions of "1".
 */
public class DecodeCompressNumber {
    public List<String> decode(String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }
        List<List<String>> dp = new ArrayList<>(s.length());
        dp.add(Collections.emptyList());
        for (int i = 1; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            List<String> current = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int count = Integer.parseInt(s.substring(j, i));
                String suffix = repeat(num, count);
                if (j == 0) {
                    current.add(suffix);
                } else {
                    for (String prefix : dp.get(j - 1)) {
                        if (prefix.charAt(prefix.length() - 1) != suffix.charAt(suffix.length() - 1)) {
                            current.add(prefix + suffix);
                        }
                    }
                }
            }
            dp.add(current);
        }
        return dp.get(s.length() - 1);
    }

    private String repeat(int num, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(num);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeCompressNumber dcn = new DecodeCompressNumber();
        System.out.println(dcn.decode("1112"));
    }
}
