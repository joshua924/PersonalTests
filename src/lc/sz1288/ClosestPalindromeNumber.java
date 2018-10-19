package lc.sz1288;

/**
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
 * <p>
 * The 'closest' is defined as absolute difference minimized between two integers.
 * <p>
 * Example 1:
 * Input: "123"
 * Output: "121"
 * Note:
 * 1. The input n is a positive integer represented by string, whose length will not exceed 18.
 * 2. If there is a tie, return the smaller one as answer.
 */
public class ClosestPalindromeNumber {
    public String nearestPalindromic(String n) {
        if (n.equals("1")) {
            return "0";
        }
        String a = mirror(n);
        long diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        if (diff1 == 0) {
            diff1 = Long.MAX_VALUE; // not including itself
        }

        StringBuilder s = new StringBuilder(n);
        int i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '0') {
            s.replace(i, i + 1, "9");
            i--;
        }
        if (i == 0 && s.charAt(i) == '1') {
            s.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid + 1, "9");
        } else {
            s.replace(i, i + 1, "" + (char) (s.charAt(i) - 1));
        }
        String b = mirror(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));

        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i + 1, "0");
            i--;
        }
        if (i < 0) {
            s.insert(0, "1");
        } else
            s.replace(i, i + 1, "" + (char) (s.charAt(i) + 1));
        String c = mirror(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        if (diff2 <= diff1 && diff2 <= diff3) {
            return b;
        }
        if (diff1 <= diff3) {
            return a;
        }
        return c;
    }

    private String mirror(String s) {
        String x = s.substring(0, (s.length()) / 2);
        return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
    }
}
