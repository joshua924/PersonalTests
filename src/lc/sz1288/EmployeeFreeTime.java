package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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
        PriorityQueue<Work> intervals = new PriorityQueue<>(schedule.size());
        for (int i = 0; i < schedule.size(); i++) {
            List<Interval> list = schedule.get(i);
            intervals.offer(new Work(i, 0, list.get(0)));
        }
        List<Interval> res = new ArrayList<>();
        int end = -1;
        while (!intervals.isEmpty()) {
            Work work = intervals.poll();
            if (work.interval.start > end && end >= 0) {
                res.add(new Interval(end, work.interval.start));
            }
            end = Math.max(end, work.interval.end);
            if (schedule.get(work.workerId).size() > work.index + 1) {
                intervals.offer(new Work(work.workerId, work.index + 1, schedule.get(work.workerId).get(work.index + 1)));
            }
        }
        return res;
    }

    private static class Work implements Comparable<Work> {
        int workerId;
        int index;
        Interval interval;

        Work(int id, int index, Interval interval) {
            this.workerId = id;
            this.index = index;
            this.interval = interval;
        }

        @Override
        public int compareTo(Work o) {
            return interval.start != o.interval.start ? interval.start - o.interval.start : interval.end - o.interval.end;
        }
    }

    public static void main(String[] args) {
        EmployeeFreeTime eft = new EmployeeFreeTime();
        List<List<Interval>> schedule = Arrays.asList(
                Arrays.asList(new Interval(1, 3), new Interval(6, 7)),
                Collections.singletonList(new Interval(2, 4)),
                Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
        System.out.println(eft.employeeFreeTime(schedule));
    }
}
