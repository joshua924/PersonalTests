package lc.sz1288;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 * <p>
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 */
public class NextClosestTime {
    public String nextClosestTime(String time) {
        Set<Integer> digits = new HashSet<>();
        for (char ch : time.toCharArray()) {
            if (ch != ':') {
                digits.add(Integer.parseInt("" + ch));
            }
        }
        Set<Integer> allPossibleTimes = new HashSet<>();
        for (int digit : digits) {
            for (int anotherDigit : digits) {
                allPossibleTimes.add(digit * 10 + anotherDigit);
            }
        }
        Set<int[]> hourAndMinute = new HashSet<>();
        for (int hour : allPossibleTimes) {
            if (hour >= 24) {
                continue;
            }
            for (int minute : allPossibleTimes) {
                if (minute >= 60) {
                    continue;
                }
                hourAndMinute.add(new int[]{hour, minute});
            }
        }
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        int[] min = new int[2];
        int minDiff = Integer.MAX_VALUE;
        for (int[] each : hourAndMinute) {
            int currentHour = each[0];
            int currentMinute = each[1];
            if (currentHour < hour || (currentHour == hour && currentMinute <= minute)) {
                currentHour += 24;
            }
            int diff = (currentHour - hour) * 60 + currentMinute - minute;
            if (diff < minDiff) {
                min = each;
                minDiff = diff;
            }
        }
        return String.format("%02d", min[0]) + ":" + String.format("%02d", min[1]);
    }

    public static void main(String[] args) {
        NextClosestTime nct = new NextClosestTime();
        System.out.println(nct.nextClosestTime("19:34"));
        System.out.println(nct.nextClosestTime("23:59"));
        System.out.println(nct.nextClosestTime("00:01"));
        System.out.println(nct.nextClosestTime("11:11"));
        System.out.println(nct.nextClosestTime("00:00"));
    }
}
