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
    public int minMeetingRooms(Interval[] endSorted) {
        Interval[] startSorted = Arrays.copyOf(endSorted, endSorted.length);
        Arrays.sort(endSorted, Comparator.comparingInt(i -> i.end));
        Arrays.sort(startSorted, Comparator.comparingInt(i -> i.start));
        int startIdx = 0;
        int endIdx = 0;
        int rooms = 0;
        int max = 0;
        while (startIdx != startSorted.length) {
            if (endSorted[endIdx].end < startSorted[startIdx].start) {
                rooms--;
                endIdx++;
            } else if (endSorted[endIdx].end > startSorted[startIdx].start) {
                rooms++;
                startIdx++;
                max = Math.max(max, rooms);
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
        System.out.println(mr.minMeetingRooms(intervals1));
        Interval[] intervals2 = {new Interval(7, 10), new Interval(2, 4)};
        System.out.println(mr.minMeetingRooms(intervals2));
        System.out.println(mr.minMeetingRooms(new Interval[]{new Interval(1, 8), new Interval(6, 20), new Interval(9, 16), new Interval(13, 17)}));
    }
}
