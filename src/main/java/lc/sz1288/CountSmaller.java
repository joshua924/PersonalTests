package lc.sz1288;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 */
public class CountSmaller {
  public List<Integer> countSmaller(int[] nums) {
    LinkedList<Integer> result = new LinkedList<>();
    List<Integer> elements = new LinkedList<>();
    for (int i = nums.length - 1; i >= 0; i--) {
      int num = nums[i];
      int j = 0;
      while (j < elements.size() && elements.get(j) < num) {
        j++;
      }
      result.addFirst(j);
      elements.add(j, num);
    }
    return result;
  }

  public static void main(String[] args) {
    CountSmaller cs = new CountSmaller();
    int[] nums = {1, 2, 2, 2, 3, 4, 5, 5};
    System.out.println(cs.countSmaller(nums));
    System.out.println(cs.countSmaller(new int[]{-1, -2}));
    System.out.println(cs.countSmaller(new int[]{5, 2, 6, 1}));
  }
}
