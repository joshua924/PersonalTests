package lc.sz1288;

public class MinDistanceRemove {
    public int minDistance(String word1, String word2) {
        int dp[][] = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int val = dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }

    public static void main(String[] args) {
        MinDistanceRemove md = new MinDistanceRemove();
        System.out.println(md.minDistance("sea", "eat"));
        System.out.println(md.minDistance("sea", "sea432456564756767"));
        System.out.println(md.minDistance("sea432456564756767", "sea"));
        System.out.println(md.minDistance("", "sea"));
        System.out.println(md.minDistance("", ""));
    }
}
