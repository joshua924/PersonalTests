package lc.sz1288;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 * <p>
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 * <p>
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 * <p>
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 * <p>
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        int s = S.length() - 1;
        int t = T.length() - 1;
        int sBack = 0;
        int tBack = 0;
        while (s >= 0 || t >= 0) {
            while (s >= 0) {
                if (S.charAt(s) == '#') {
                    sBack++;
                    s--;
                } else if (sBack > 0) {
                    sBack--;
                    s--;
                } else {
                    break;
                }
            }
            while (t >= 0) {
                if (T.charAt(t) == '#') {
                    tBack++;
                    t--;
                } else if (tBack > 0) {
                    tBack--;
                    t--;
                } else {
                    break;
                }
            }
            if (s >= 0 && t >= 0 && S.charAt(s) != T.charAt(t)) {
                return false;
            }
            if (s >= 0 ^ t >= 0) {
                return false;
            }
            s--;
            t--;
        }
        return true;
    }

    public static void main(String[] args) {
        BackspaceStringCompare bsc = new BackspaceStringCompare();
        System.out.println(bsc.backspaceCompare("###ab#c", "ac"));
    }
}
