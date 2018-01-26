package lc.sz1288;

public class FreedomTrail {
    public int findRotateSteps(String ring, String key) {
        int ringLength = ring.length();
        int keyLength = key.length();
        int[][] dp = new int[keyLength + 1][ringLength];

        for (int i = keyLength - 1; i >= 0; i--) {
            char target = key.charAt(i);
            for (int j = 0; j < ringLength; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < ringLength; k++) {
                    if (ring.charAt(k) == target) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, ringLength - diff);
                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }
        return dp[0][0] + keyLength;
    }

    public static void main(String[] args) {
        FreedomTrail ft = new FreedomTrail();
        System.out.println(ft.findRotateSteps("g", "g"));
        System.out.println(ft.findRotateSteps("godding", "gd"));
        System.out.println(ft.findRotateSteps("geometric", "emit"));
    }
}
