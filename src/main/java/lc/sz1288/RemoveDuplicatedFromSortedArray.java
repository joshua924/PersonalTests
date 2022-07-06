package lc.sz1288;

import java.util.Arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 */
public class RemoveDuplicatedFromSortedArray {
  /**
   * The idea is to use 'next' to track the next non-duplicate value, and then use 'current' to
   * track the current position that we want to write value to. We always move 'next' to the next
   * non-duplicate value and copy it to the current position, then we move both current and next
   * forward until the end is reached.
   */
  public int removeDuplicates(int[] nums) {
    int current = 0;
    int next = 1;
    while (next < nums.length) {
      while (next < nums.length && nums[next] == nums[next - 1]) {
        next++;
      }
      if (next == nums.length) {
        break;
      }
      current++;
      nums[current] = nums[next];
      next++;
    }
    return current + 1;
  }

  public static void main(String[] args) {
    RemoveDuplicatedFromSortedArray solution = new RemoveDuplicatedFromSortedArray();
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    int k = solution.removeDuplicates(nums);
    System.out.printf("First %d elements are now %s%n", k, Arrays.toString(Arrays.copyOf(nums, k)));
    int[] nums1 = {0, 0, 0, 0, 0, 0, 0, 0};
    int k1 = solution.removeDuplicates(nums1);
    System.out.printf("First %d elements are now %s%n", k1, Arrays.toString(Arrays.copyOf(nums1, k1)));
  }
}
