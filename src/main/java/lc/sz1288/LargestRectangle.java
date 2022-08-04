package lc.sz1288;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 */
public class LargestRectangle {
  public int largestRectangleArea(int[] heights) {
    Deque<Integer> deque = new LinkedList<>();
    deque.push(-1);
    int max = 0;
    for (int i = 0; i < heights.length; i++) {
      while (deque.peek() != -1 && heights[deque.peek()] >= heights[i]) {
        int currentHeight = heights[deque.pop()];
        max = Math.max(max, currentHeight * (i - deque.peek() - 1));
      }
      deque.push(i);
    }
    while (deque.peek() != -1) {
      int currentHeight = heights[deque.pop()];
      int currentWidth = heights.length - deque.peek() - 1;
      max = Math.max(max, currentHeight * currentWidth);
    }
    return max;
  }

  public int largestRectangleAreaN2(int[] heights) {
    int n = heights.length;
    int max = 0;
    for (int i = 0; i < n; i++) {
      int min = Integer.MAX_VALUE;
      for (int j = i; j < n; j++) {
        min = Integer.min(min, heights[j]);
        max = Integer.max(max, min * (j - i + 1));
      }
    }
    return max;
  }

  public static void main(String[] args) {
    LargestRectangle lr = new LargestRectangle();
    int[] heights = {2, 1, 5, 6, 2, 3};
    System.out.println(lr.largestRectangleArea(heights));
    System.out.println(lr.largestRectangleAreaN2(heights));
  }
}
