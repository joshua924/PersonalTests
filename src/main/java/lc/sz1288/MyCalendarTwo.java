package lc.sz1288;

import java.util.TreeMap;

/**
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 * <p>
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 * <p>
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 * <p>
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event
 * to the calendar.
 * <p>
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 */
public class MyCalendarTwo {
    private final TreeMap<Integer, Integer> delta;

    public MyCalendarTwo() {
        delta = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d : delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0) {
                    delta.remove(start);
                }
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo mc2 = new MyCalendarTwo();
        mc2.book(89, 100);
        mc2.book(30, 43);
        mc2.book(92, 100);
        mc2.book(31, 49);
        mc2.book(59, 76);
        mc2.book(60, 73);
        mc2.book(31, 49);
        mc2.book(80, 99);
        mc2.book(48, 60);
        mc2.book(36, 52);
        System.out.println(mc2.book(67, 82));
        System.out.println(mc2.book(96, 100));
    }
}
