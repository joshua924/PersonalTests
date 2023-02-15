package lc.sz1288;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder_MinMaxHeap implements MedianFinder {
  private final PriorityQueue<Integer> maxQueue;
  private final PriorityQueue<Integer> minQueue;

  public MedianFinder_MinMaxHeap() {
    maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
    minQueue = new PriorityQueue<>();
  }

  @Override
  public void addNum(int num) {
    maxQueue.offer(num);
    minQueue.offer(maxQueue.poll());
    if (minQueue.size() > maxQueue.size()) {
      maxQueue.offer(minQueue.poll());
    }
  }

  @Override
  public double findMedian() {
    int total = maxQueue.size() + minQueue.size();
    return total % 2 == 0 ? maxQueue.peek() * 0.5 + minQueue.peek() * 0.5 : maxQueue.peek();
  }

  public static void main(String[] args) {
    MedianFinder_MinMaxHeap solution = new MedianFinder_MinMaxHeap();
    solution.addNum(1);
    solution.addNum(3);
    System.out.println(solution.findMedian());
    solution.addNum(3);
    System.out.println(solution.findMedian());
  }
}
