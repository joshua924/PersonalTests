package lc.sz1288;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        LinkedList<Interval> res = new LinkedList<>();
        intervals.sort(Comparator.comparingInt(i -> i.start));
        for (Interval interval : intervals) {
            int currentEnd = res.getLast().end;
            if (res.isEmpty() || interval.start > currentEnd) {
                res.add(interval);
            } else {
                res.getLast().end = Math.max(currentEnd, interval.end);
            }
        }
        return res;
    }
}
