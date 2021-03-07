package lc.sz1288;

/**
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 */
public class NumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return 0;
        }
        int[] longestLength = new int[size];
        int[] count = new int[size];

        for (int i = 0; i < size; i++) {
            longestLength[i] = 1;
            count[i] = 1;
        }
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (longestLength[i] < longestLength[j] + 1) {
                        longestLength[i] = longestLength[j] + 1;
                        count[i] = count[j];
                    } else if (longestLength[i] == longestLength[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int num : longestLength) {
            max = Math.max(max, num);
        }
        int res = 0;
        for (int i = 0; i < longestLength.length; i++) {
            int length = longestLength[i];
            if (length == max) {
                res += count[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        NumberOfLIS nol = new NumberOfLIS();
        System.out.println(nol.findNumberOfLIS(new int[]{}));
        System.out.println(nol.findNumberOfLIS(new int[]{3}));
        System.out.println(nol.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
        System.out.println(nol.findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2}));
    }
}
