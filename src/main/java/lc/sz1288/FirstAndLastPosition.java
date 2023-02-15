package lc.sz1288;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class FirstAndLastPosition {
  public int[] searchRange(int[] nums, int target) {
    return new int[]{
        binarySearch(nums, target, true),
        binarySearch(nums, target, false)
    };
  }

  private int binarySearch(int[] nums, int target, boolean findFirst) {
    int begin = 0;
    int end = nums.length - 1;

    while (begin <= end) {
      int mid = (begin + end) / 2;
      if (nums[mid] < target) {
        begin = mid + 1;
      } else if (nums[mid] > target) {
        end = mid - 1;
      } else {
        if (findFirst) {
          if (mid == begin || nums[mid - 1] != target) {
            return mid;
          }
          end = mid - 1;
        } else {
          if (mid == end || nums[mid + 1] != target) {
            return mid;
          }
          begin = mid + 1;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    FirstAndLastPosition solution = new FirstAndLastPosition();
    int[] nums1 = {5, 7, 7, 8, 8, 10};
    System.out.println(Arrays.toString(solution.searchRange(nums1, 8)));
    int[] nums2 = {5, 5, 5, 5};
    System.out.println(Arrays.toString(solution.searchRange(nums2, 8)));
    System.out.println(Arrays.toString(solution.searchRange(nums2, 5)));
  }
}
