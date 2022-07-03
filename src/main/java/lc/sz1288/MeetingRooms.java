package lc.sz1288;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
public class MeetingRooms {
  public int minMeetingRooms(int[][] intervals) {
    int[][] sortedByStart = Arrays.copyOf(intervals, intervals.length);
    Arrays.sort(sortedByStart, Comparator.comparingInt(i -> i[0]));
    Arrays.sort(intervals, Comparator.comparingInt(i -> i[1]));

    int i = 0;
    int j = 0;
    int rooms = 0;
    int max = 0;
    while (i < intervals.length && j < intervals.length) {
      if (sortedByStart[i][0] < intervals[j][1]) {
        rooms++;
        i++;
        max = Math.max(max, rooms);
      } else {
        rooms--;
        j++;
      }
    }
    return max;
  }

  public boolean canAttendMeetings(Interval[] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(i -> i.end));
    for (int i = 0; i < intervals.length - 1; i++) {
      Interval current = intervals[i];
      if (current.end > intervals[i + 1].start) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    MeetingRooms mr = new MeetingRooms();
    Interval[] intervals1 = {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)};
    System.out.println(mr.canAttendMeetings(intervals1));

    int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
    System.out.println(mr.minMeetingRooms(intervals));
    System.out.println(mr.minMeetingRooms(new int[][]{{1, 5}, {5, 8}}));
  }
}
