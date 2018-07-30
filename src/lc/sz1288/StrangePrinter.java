package lc.sz1288;

/**
 * There is a strange printer with the following two special requirements:
 * <p>
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 * Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
 */
public class StrangePrinter {
    public int strangePrinter(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; j + i < s.length(); i++) {
                dp[i][i + j] = j + 1;
                for (int k = i; k < i + j; k++) {
                    int sum = dp[i][k] + dp[k + 1][i + j];
                    if (s.charAt(k) == s.charAt(i + j)) {
                        dp[i][i + j] = Math.min(dp[i][i + j], sum - 1);
                    } else {
                        dp[i][i + j] = Math.min(dp[i][i + j], sum);
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        StrangePrinter sp = new StrangePrinter();
        System.out.println(sp.strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"));
    }
}
