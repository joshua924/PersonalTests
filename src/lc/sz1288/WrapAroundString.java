package lc.sz1288;

public class WrapAroundString {
    public int findSubstringInWrapAroundString(String p) {
        int[] maxLengthForCh = new int[26];
        int res = 0;
        int index = 0;
        while (index < p.length()) {
            int current = index + 1;
            while (current < p.length() && (p.charAt(current) - p.charAt(current - 1) == 1 || (p.charAt(current) == 'a' && p.charAt(current - 1) == 'z'))) {
                current++;
            }
            res += computeNewSubstrings(maxLengthForCh, p.substring(index, current));
            index = current;
        }
        return res;
    }

    private int computeNewSubstrings(int[] maxLengthForCh, String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int offset = s.charAt(i) - 'a';
            int remainingLength = s.length() - i;
            int maxLength = maxLengthForCh[offset];
            if (remainingLength > maxLength) {
                res += remainingLength - maxLength;
                maxLengthForCh[offset] = remainingLength;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        WrapAroundString was = new WrapAroundString();
        System.out.println(was.findSubstringInWrapAroundString("abcabcde"));
        System.out.println(was.findSubstringInWrapAroundString("za"));
        System.out.println(was.findSubstringInWrapAroundString("abcdefg"));
        System.out.println(was.findSubstringInWrapAroundString("haxosdyhsiugfdsuiuftygdsuyifgisdfghdhjksbfvadskdhasuioetyd"));
    }
}
