package lc.sz1288;

public class DecodeWay {
    public int numDecodings(String s) {
        int[] numWays = new int[s.length() + 1];
        numWays[0] = 1;
        numWays[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                numWays[i + 1] += numWays[i];
            }
            int val = Integer.parseInt(s.substring(i - 1, i + 1));
            if (val <= 26 && val >= 10) {
                numWays[i + 1] += numWays[i - 1];
            }
        }
        return numWays[s.length()];
    }

    public static void main(String[] args) {
        DecodeWay decodeWay = new DecodeWay();
        System.out.println(decodeWay.numDecodings("0253"));
        System.out.println(decodeWay.numDecodings("30"));
        System.out.println(decodeWay.numDecodings("1"));
        System.out.println(decodeWay.numDecodings("12"));
        System.out.println(decodeWay.numDecodings("226"));
    }
}
