package lc.sz1288;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        boolean less = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (less && nums[i] > nums[i + 1] || !less && nums[i] < nums[i + 1]) {
                swap(nums, i, i + 1);
            }
            less = !less;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
     */
    public static class WiggleSortII {
        public void wiggleSort(int[] nums) {
            boolean less = true;
            for (int i = 0; i < nums.length - 1; i++) {
                int j = i + 1;
                while (j < nums.length && nums[j] == nums[i]) {
                    j++;
                }
                if (less) {
                    if (nums[j] > nums[i]) {
                        swap(nums, i + 1, j);
                    } else {
                        swap(nums, i, j);
                    }
                } else {
                    if (nums[j] > nums[i]) {
                        swap(nums, i, j);
                    } else {
                        swap(nums, i + 1, j);
                    }
                }
                less = !less;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        WiggleSortII ws = new WiggleSortII();
        int[] arr = {1, 3, 2, 2, 3, 1};
        ws.wiggleSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{1, 2};
        ws.wiggleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
