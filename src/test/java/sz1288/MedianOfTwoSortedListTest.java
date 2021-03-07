package sz1288;

import lc.sz1288.MedianOfTwoSortedList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedianOfTwoSortedListTest {

    private MedianOfTwoSortedList solution = new MedianOfTwoSortedList();

    @Test
    public void testCase1() {
        int[] nums1 = {1, 3};
        int[] nums2 = {};
        assertDoubleEquals(2, solution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testCase2() {
        int[] nums1 = {10000};
        int[] nums2 = {-5, 0};
        assertDoubleEquals(0, solution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testCase3() {
        int[] nums1 = {2, 3};
        int[] nums2 = {1, 4};
        assertDoubleEquals(2.5, solution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testCase4() {
        int[] nums1 = {1, 2, 3, 4, 6, 7, 8, 9};
        int[] nums2 = {5};
        assertDoubleEquals(5, solution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testCase5() {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] nums2 = {1, 2, 3, 4, 6, 7, 8, 9, 10, 11};
        assertDoubleEquals(5.5, solution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void testCase6() {
        int[] nums1 = {Integer.MIN_VALUE, 0};
        int[] nums2 = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        assertDoubleEquals(Integer.MAX_VALUE, solution.findMedianSortedArrays(nums1, nums2));
    }

    private void assertDoubleEquals(double d1, double d2) {
        assertEquals(d1, d2, 0.00001);
    }
}