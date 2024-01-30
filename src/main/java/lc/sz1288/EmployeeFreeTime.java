package lc.sz1288;

import java.util.*;

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
@SuppressWarnings("ConstantConditions")
public class EmployeeFreeTime {
  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    PriorityQueue<int[]> queue =
        new PriorityQueue<>(Comparator.comparingInt(s -> schedule.get(s[0]).get(s[1]).start));
    for (int i = 0; i < schedule.size(); i++) {
      queue.offer(new int[] {i, 0});
    }

    List<Interval> res = new LinkedList<>();
    int end = schedule.get(queue.peek()[0]).get(queue.peek()[1]).end;
    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      Interval interval = schedule.get(current[0]).get(current[1]);
      if (end < interval.start) {
        res.add(new Interval(end, interval.start));
      }
      end = Integer.max(end, interval.end);
      if (schedule.get(current[0]).size() > current[1] + 1) {
        queue.offer(new int[]{current[0], current[1] + 1});
      }
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
