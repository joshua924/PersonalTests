package lc.sz1288;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
@SuppressWarnings("ConstantConditions")
public class KthLargestElement {
  public int findKthLargest(int[] nums, int k) {
    Queue<Integer> queue = new PriorityQueue<>(k);
    for (int num : nums) {
      if (queue.size() == k) {
        int smallestInQueue = queue.peek();
        if (smallestInQueue < num) {
          queue.poll();
          queue.offer(num);
        }
      } else {
        queue.offer(num);
      }
    }
    return queue.peek();
  }

  public static void main(String[] args) {
    KthLargestElement solution = new KthLargestElement();
    System.out.println(solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    System.out.println(solution.findKthLargest(new int[]{9}, 1));
  }
}
