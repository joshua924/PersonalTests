package lc.sz1288;

import java.util.Arrays;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] slots = new int[26];
        for (char t : tasks) {
            slots[t - 'A']++;
        }
        Arrays.sort(slots);
        int i = 25;
        while (i >= 0 && slots[i] == slots[25]) {
            i--;
        }
        return Math.max(tasks.length, (slots[25] - 1) * (n + 1) + 25 - i);
    }

    public static void main(String[] args) {
        TaskScheduler ts = new TaskScheduler();
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(ts.leastInterval(tasks, 2));
    }
}
