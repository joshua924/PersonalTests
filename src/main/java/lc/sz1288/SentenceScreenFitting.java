package lc.sz1288;

/**
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.
 * <p>
 * Note:
 * <p>
 * A word cannot be split into two lines.
 * The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Length of each word is greater than 0 and won't exceed 10.
 * 1 ≤ rows, cols ≤ 20,000.
 */
public class SentenceScreenFitting {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] dp = new int[sentence.length];
        int n = sentence.length;
        for (int i = 0, prev = 0, len = 0; i < sentence.length; i++) {
            if (i != 0 && len > 0) {
                len -= sentence[i - 1].length() + 1;
            }
            while (len + sentence[prev % n].length() <= cols) {
                len += sentence[prev++ % n].length() + 1;
            }
            dp[i] = prev;
        }
        int count = 0;
        for (int i = 0, k = 0; i < rows; i++) {
            count += dp[k] - k;
            k = dp[k] % n;
        }
        return count / n;
    }
}
