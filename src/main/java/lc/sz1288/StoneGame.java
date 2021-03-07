package lc.sz1288;

/**
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * <p>
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 * <p>
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more
 * piles left, at which point the person with the most stones wins.
 * <p>
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 */
public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        for (int i = 0; i < piles.length; i++) {
            dp[i][i] = piles[i];
        }
        for (int j = 2; j <= piles.length; j++) {
            for (int i = 0; i + j <= piles.length; i++) {
                dp[i][i + j - 1] = Math.max(piles[i] - dp[i + 1][i + j - 1], piles[i + j - 1] - dp[i][i + j - 2]);
            }
        }
        return dp[0][piles.length - 1] > 0;
    }

    public static void main(String[] args) {
        StoneGame sg = new StoneGame();
        System.out.println(sg.stoneGame(new int[]{5, 3, 4, 5}));
    }
}
