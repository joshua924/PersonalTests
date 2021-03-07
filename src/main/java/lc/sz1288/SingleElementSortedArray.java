package lc.sz1288;

public class SingleElementSortedArray {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return binarySearch(nums, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int start, int end) {
        if (start >= end - 1) {
            return (start % 2 == 0) ? nums[start] : nums[end];
        }
        int mid = (start + end) / 2;
        if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
            return nums[mid];
        }
        if (mid % 2 == 0) {
            if (nums[mid] == nums[mid + 1]) {
                return binarySearch(nums, mid + 1, end);
            } else {
                return binarySearch(nums, start, mid - 1);
            }
        } else {
            if (nums[mid] == nums[mid + 1]) {
                return binarySearch(nums, start, mid - 1);
            } else {
                return binarySearch(nums, mid + 1, end);
            }
        }
    }
}
