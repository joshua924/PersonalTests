package lc.sz1288;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,0]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [3,4,-1,1]
 * Output: 2
 * Example 3:
 * <p>
 * Input: [7,8,9,11,12]
 * Output: 1
 * Note:
 * <p>
 * Your algorithm should run in O(n) time and uses constant extra space.
 * <p>
 * <p>
 * The idea is to put each positive number that's smaller the length to the index (number - 1)
 * so that 1 will be at index 0, 2 will be at index 1, etc, if they do exist.
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int index = 0;
        while (index < nums.length) {
            if (nums[index] == index + 1 || nums[index] <= 0 || nums[index] > nums.length || nums[nums[index] - 1] == nums[index]) {
                index++;
            } else {
                swap(nums, index, nums[index] - 1);
            }
        }
        int i = 0;
        while (i < nums.length && nums[i] == i + 1) {
            i++;
        }
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        FirstMissingPositive fmp = new FirstMissingPositive();
        System.out.println(fmp.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(fmp.firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(fmp.firstMissingPositive(new int[]{7, 8, 9}));
    }
}
