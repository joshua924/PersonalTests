package lc.sz1288;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, you need to return a string instead of an integer.
 */
public class LargestNumber {
  public String largestNumber(int[] nums) {
    List<String> sorted = IntStream
        .of(nums)
        .mapToObj(String::valueOf)
        .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
        .collect(Collectors.toList());
    String result = String.join("", sorted);
    return result.matches("0+") ? "0" : result;
  }

  public static void main(String[] args) {
    LargestNumber solution = new LargestNumber();
    int[] nums = {3, 30, 34, 5, 9};
    System.out.println(solution.largestNumber(nums));
    System.out.println(solution.largestNumber(new int[]{0, 0, 0}));
  }
}
