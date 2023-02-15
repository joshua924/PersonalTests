package lc.sz1288;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Could you do it in-place with O(1) extra space?
 */
public class RotateArray {
  // cyclic replacement
  public void rotate(int[] nums, int k) {
    for (int i = 0, count = 0; count < nums.length; i++) {
      int index = i;
      int previous = nums[index];
      do {
        int next = (index + k) % nums.length;
        int temp = nums[next];
        nums[next] = previous;
        previous = temp;
        index = next;
        count++;
      } while (index != i);
    }
  }

  public static void main(String[] args) {
    RotateArray solution = new RotateArray();
    int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    solution.rotate(nums, 2);
    System.out.println(Arrays.toString(nums));
    solution.rotate(nums, 5);
    System.out.println(Arrays.toString(nums));
  }
}
