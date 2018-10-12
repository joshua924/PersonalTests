package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * For example,
 * [2,3,4], the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * <p>
 * 1. If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * 2. If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFinder_Basic implements MedianFinder {
    private List<Integer> nums;

    /**
     * initialize your data structure here.
     */
    public MedianFinder_Basic() {
        nums = new ArrayList<>();
    }

    @Override
    public void addNum(int num) {
        int index = Collections.binarySearch(nums, num);
        if (index < 0) {
            index = -(index + 1);
        }
        nums.add(index, num);
    }

    @Override
    public double findMedian() {
        int size = nums.size();
        if (size % 2 == 0) {
            return nums.get(size / 2) / 2.0 + nums.get(size / 2 - 1) / 2.0;
        } else {
            return nums.get(size / 2);
        }
    }

    public static void main(String[] args) {
        MedianFinder_Basic mf = new MedianFinder_Basic();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
    }
}
