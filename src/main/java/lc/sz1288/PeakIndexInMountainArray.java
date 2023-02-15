package lc.sz1288;

/**
 * An array arr a mountain if the following properties hold:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array arr, return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 *
 * You must solve it in O(log(arr.length)) time complexity.
 */
public class PeakIndexInMountainArray {
  public int peakIndexInMountainArray(int[] arr) {
    int low = 0;
    int high = arr.length - 1;
    while (low < high) {
      int mid = (low + high) / 2;
      if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
        return mid;
      } else if (arr[mid] > arr[mid - 1]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    PeakIndexInMountainArray solution = new PeakIndexInMountainArray();
    System.out.println(solution.peakIndexInMountainArray(new int[]{0, 2, 3, 5, 3}));
    System.out.println(solution.peakIndexInMountainArray(new int[]{3, 5, 3, 2, 0}));
    System.out.println(solution.peakIndexInMountainArray(new int[]{0, 1, 2, 0}));
    System.out.println(solution.peakIndexInMountainArray(new int[]{0, 3, 2, 0}));
  }
}
