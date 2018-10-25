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

    public void wiggleSortII(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int[] res = new int[nums.length];
        int odd = 1;
        int even = nums.length % 2 == 0 ? nums.length - 2 : nums.length - 1;
        for (int num : nums) {
            if (num < median) {
                res[even] = num;
                even -= 2;
            } else if (num > median) {
                res[odd] = num;
                odd += 2;
            }
        }
        while (odd < nums.length) {
            res[odd] = median;
            odd += 2;
        }
        while (even >= 0) {
            res[even] = median;
            even -= 2;
        }
        System.arraycopy(res, 0, nums, 0, nums.length);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        int idx = -1;
        int left = 0, right = nums.length - 1;
        while (idx != k - 1) {
            idx = partition(nums, left, right);
            if (idx > k - 1) {
                right = idx - 1;
            } else {
                left = idx + 1;
            }
        }
        return nums[idx];
    }

    private int partition(int[] nums, int low, int high) {
        int index = low;
        int pivot = nums[high];
        for (int i = low; i < high; i++) {
            if (nums[i] > pivot) {
                swap(nums, index, i);
                index++;
            }
        }
        swap(nums, index, high);
        return index;
    }

    public static void main(String[] args) {
        WiggleSort ws = new WiggleSort();
        int[] arr = {1, 5, 1, 1, 6, 4};
        ws.wiggleSortII(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{4, 4, 5, 5, 6};
        ws.wiggleSortII(arr);
        System.out.println(Arrays.toString(arr));
    }
}
