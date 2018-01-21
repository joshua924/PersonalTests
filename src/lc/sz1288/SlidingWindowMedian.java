package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Integer> current = new ArrayList<>();
        double[] res = new double[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            int index = Collections.binarySearch(current, nums[i]);
            if (index < 0) {
                index = -index - 1;
            }
            current.add(index, nums[i]);
        }
        res[0] = getMedian(current);
        for (int i = k; i < nums.length; i++) {
            int remove = Collections.binarySearch(current, nums[i - k]);
            current.remove(remove);
            int index = Collections.binarySearch(current, nums[i]);
            if (index < 0) {
                index = -index - 1;
            }
            current.add(index, nums[i]);
            res[i - k + 1] = getMedian(current);
        }
        return res;
    }

    private double getMedian(List<Integer> current) {
        int size = current.size();
        if (size % 2 == 0) {
            return current.get(size / 2 - 1) / 2.0 + current.get(size / 2) / 2.0;
        } else {
            return (double) current.get(size / 2);
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {2147483647, 2147483647};
        double[] medians = swm.medianSlidingWindow(nums, 2);
        for (double median : medians) {
            System.out.print(median + " ");
        }
    }
}
