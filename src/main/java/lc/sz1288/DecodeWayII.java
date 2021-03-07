package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class DecodeWayII {
    private static final int MOD = 1000000007;

    public int numDecodings(String s) {
        Map<Character, Integer> wildCardMap = new HashMap<>();
        wildCardMap.put('1', 9);
        wildCardMap.put('2', 6);
        wildCardMap.put('*', 15);
        long[] numWays = new long[s.length() + 1];
        numWays[0] = 1;
        numWays[1] = s.charAt(0) == '0' ? 0 : s.charAt(0) == '*' ? 9 : 1;
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                numWays[i + 1] += numWays[i] * 9;
                numWays[i + 1] %= MOD;
                if (wildCardMap.containsKey(s.charAt(i - 1))) {
                    numWays[i + 1] += (wildCardMap.get(s.charAt(i - 1)) * numWays[i - 1]) % MOD;
                    numWays[i + 1] %= MOD;
                }
            } else {
                if (ch != '0') {
                    numWays[i + 1] += numWays[i] % MOD;
                    numWays[i + 1] %= MOD;
                }
                if (s.charAt(i - 1) != '*') {
                    int val = Integer.parseInt(s.substring(i - 1, i + 1));
                    if (val <= 26 && val >= 10) {
                        numWays[i + 1] += numWays[i - 1] % MOD;
                        numWays[i + 1] %= MOD;
                    }
                } else {
                    if (ch <= '6') {
                        numWays[i + 1] += (numWays[i - 1] * 2) % MOD;
                        numWays[i + 1] %= MOD;
                    } else {
                        numWays[i + 1] += numWays[i - 1] % MOD;
                        numWays[i + 1] %= MOD;
                    }
                }
            }
        }
        return (int) (numWays[s.length()] % MOD);
    }

    public static void main(String[] args) {
        DecodeWayII dw2 = new DecodeWayII();
        System.out.println(dw2.numDecodings("*1*1*0"));
    }
}
