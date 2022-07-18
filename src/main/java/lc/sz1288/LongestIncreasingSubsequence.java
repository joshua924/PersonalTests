package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */
public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];

    for (int i = nums.length - 1; i >= 0; i--) {
      dp[i] = 1;
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j] > nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    int res = 0;
    for (int len : dp) {
      res = Math.max(res, len);
    }
    return res;
  }

  public int lengthOfLIS_nlogn(int[] nums) {
    List<Integer> tails = new ArrayList<>();
    for (int num : nums) {
      int index = Collections.binarySearch(tails, num);
      index = index < 0 ? -index - 1 : index;
      if (index == tails.size()) {
        tails.add(num);
      } else {
        tails.set(index, num);
      }
    }
    return tails.size();
  }

  public static void main(String[] args) {
    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    System.out.println(lis.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println(lis.lengthOfLIS_nlogn(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
  }
}
