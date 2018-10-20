package lc.sz1288;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            while (j < t.length() && t.charAt(j) != s.charAt(i)) {
                j++;
            }
            if (j == t.length()) {
                return false;
            }
            i++;
            j++;
        }
        return i == s.length();
    }

    public static void main(String[] args) {
        IsSubsequence is = new IsSubsequence();
        System.out.println(is.isSubsequence("a", "a"));
        System.out.println(is.isSubsequence("a", ""));
        System.out.println(is.isSubsequence("", ""));
        System.out.println(is.isSubsequence("abc", "ahbgdc"));
        System.out.println(is.isSubsequence("axc", "ahbgdc"));
    }
}
