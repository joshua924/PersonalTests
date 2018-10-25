package lc.sz1288;

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
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

    private void swap(int[] nums, int idx1, int idx2) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }
}
