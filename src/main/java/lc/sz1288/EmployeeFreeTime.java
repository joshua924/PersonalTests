package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 * <p>
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * <p>
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * <p>
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and
 * schedule[0][0][0] is not defined.)
 * <p>
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 * <p>
 * Note:
 * <p>
 * 1. schedule and schedule[i] are lists with lengths in range [1, 50].
 * 2. 0 <= schedule[i].start < schedule[i].end <= 10^8.
 */
public class EmployeeFreeTime {
  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    List<Interval> sorted = schedule
        .stream()
        .flatMap(List::stream)
        .sorted((i1, i2) -> i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start)
        .collect(Collectors.toList());
    List<Interval> res = new ArrayList<>();
    int end = sorted.get(0).end;
    for (Interval interval : sorted) {
      if (interval.start > end) {
        res.add(new Interval(end, interval.start));
      }
      end = Integer.max(interval.end, end);
    }
    return res;
  }

  public static void main(String[] args) {
    EmployeeFreeTime eft = new EmployeeFreeTime();
    List<List<Interval>> schedule1 = Arrays.asList(
        Arrays.asList(new Interval(1, 3), new Interval(6, 7)),
        Collections.singletonList(new Interval(2, 4)),
        Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
    System.out.println(eft.employeeFreeTime(schedule1));
    List<List<Interval>> schedule2 = Arrays.asList(
        Arrays.asList(new Interval(0, 25), new Interval(30, 40)),
        Arrays.asList(new Interval(4, 16), new Interval(20, 28)));
    System.out.println(eft.employeeFreeTime(schedule2));
  }
}
