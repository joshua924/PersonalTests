package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 * <p>
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 * <p>
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 * <p>
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event
 * to the calendar.
 * <p>
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 */
public class MyCalendar {
    private static final Comparator<int[]> COMPARATOR = (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];

    private final List<int[]> intervals;

    public MyCalendar() {
        intervals = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        int[] insert = new int[]{start, end};
        int index = Collections.binarySearch(intervals, insert, COMPARATOR);
        if (index < 0) {
            index = -index - 1;
        }
        if (index != 0 && intervals.get(index - 1)[1] > start) {
            return false;
        }
        if (index != intervals.size() && intervals.get(index)[0] < end) {
            return false;
        }
        intervals.add(index, insert);
        return true;
    }

    public static void main(String[] args) {
        MyCalendar mc = new MyCalendar();
        System.out.println(mc.book(10, 20));
        System.out.println(mc.book(15, 25));
        System.out.println(mc.book(20, 30));
    }
}
