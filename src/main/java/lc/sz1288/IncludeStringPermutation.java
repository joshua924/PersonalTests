package lc.sz1288;

import java.util.Arrays;

public class IncludeStringPermutation {
    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 > l2) {
            return false;
        }
        int[] s1Count = convertCount(s1);
        int[] s2Count = convertCount(s2.substring(0, l1));
        if (Arrays.equals(s1Count, s2Count)) {
            return true;
        }
        for (int i = l1; i < l2; i++) {
            int add = s2.charAt(i) - 'a';
            int delete = s2.charAt(i - l1) - 'a';
            s2Count[add]++;
            s2Count[delete]--;
            if (Arrays.equals(s1Count, s2Count)) {
                return true;
            }
        }
        return false;
    }

    private int[] convertCount(String s) {
        int[] res = new int[26];
        for (char ch : s.toCharArray()) {
            res[ch - 'a']++;
        }
        return res;
    }

    public static void main(String[] args) {
        IncludeStringPermutation isp = new IncludeStringPermutation();
        System.out.println(isp.checkInclusion("aboo", "eibaooo"));
    }
}
