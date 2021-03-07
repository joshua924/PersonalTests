package lc.sz1288;

import java.util.Arrays;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int pairs = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);
        for (int left = start, right = mid + 1; left <= mid; left++) {
            while (right <= end && nums[left] / 2.0 > nums[right]) {
                right++;
            }
            pairs += right - (mid + 1);
        }
        Arrays.sort(nums, start, end + 1);
        return pairs;
    }

    public static void main(String[] args) {
        ReversePairs rp = new ReversePairs();
        int[] nums1 = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        int[] nums2 = {1, 3, 2, 3, 1};
        int[] nums3 = {2, 4, 3, 5, 1};
        int[] nums4 = {-5, -5};
        int[] nums5 = {-2147483646, -2147483646};
        System.out.println(rp.reversePairs(nums1));
        System.out.println(rp.reversePairs(nums2));
        System.out.println(rp.reversePairs(nums3));
        System.out.println(rp.reversePairs(nums4));
        System.out.println(rp.reversePairs(nums5));
    }

//    time limit exceeded
//    public int reversePairs(int[] nums) {
//        List<Integer> current = new ArrayList<>();
//        int pair = 0;
//        for (int num : nums) {
//            int target = 2 * num;
//            if ((num > 0 && target > 0) || (num < 0 && target < 0) || num == 0) {
//                int index = Collections.binarySearch(current, target);
//                if (index >= 0) {
//                    while (index < current.size() && current.get(index) == target) {
//                        index++;
//                    }
//                } else {
//                    index = -index - 1;
//                }
//                pair += current.size() - index;
//            } else if (num < 0) {
//                pair += current.size();
//            }
//            int index = Collections.binarySearch(current, num);
//            if (index < 0) {
//                index = -index - 1;
//            }
//            current.add(index, num);
//        }
//        return pair;
//    }
}
