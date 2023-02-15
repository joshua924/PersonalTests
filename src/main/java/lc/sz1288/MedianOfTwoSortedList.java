package lc.sz1288;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 */
public class MedianOfTwoSortedList {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int size = nums1.length + nums2.length;
    int i = 0, j = 0;
    int median = 0;
    int pre = 0;
    while (i + j <= size / 2) {
      pre = median;
      if (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
          median = nums1[i];
          i++;
        } else {
          median = nums2[j];
          j++;
        }
      } else if (i == nums1.length) {
        median = nums2[j];
        j++;
      } else {
        median = nums1[i];
        i++;
      }
    }
    return size % 2 == 0 ? pre * 0.5 + median * 0.5 : median;
  }
}
