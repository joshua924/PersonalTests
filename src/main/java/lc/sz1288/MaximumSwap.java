package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 *
 * Return the maximum valued number you can get.
 */
public class MaximumSwap {
  public int maximumSwap(int num) {
    List<Integer> nums = fromNum(num);
    List<Integer> sorted = new ArrayList<>(nums);
    sorted.sort(Comparator.reverseOrder());

    for (int i = 0; i < nums.size(); i++) {
      if (nums.get(i) != sorted.get(i)) {
        swap(nums, i, sorted.get(i));
        break;
      }
    }
    return toNum(nums);
  }

  private void swap(List<Integer> nums, int i, int newValue) {
    int j = nums.lastIndexOf(newValue);
    int temp = nums.get(i);
    nums.set(i, newValue);
    nums.set(j, temp);
  }

  private List<Integer> fromNum(int num) {
    List<Integer> res = new ArrayList<>();
    while (num > 0) {
      res.add(0, num % 10);
      num /= 10;
    }
    return res;
  }

  private int toNum(List<Integer> list) {
    int num = 0;
    for (int each : list) {
      num *= 10;
      num += each;
    }
    return num;
  }

  public static void main(String[] args) {
    MaximumSwap ms = new MaximumSwap();
    System.out.println(ms.maximumSwap(9932));
    System.out.println(ms.maximumSwap(2736));
    System.out.println(ms.maximumSwap(95146));
    System.out.println(ms.maximumSwap(98368));
  }
}
