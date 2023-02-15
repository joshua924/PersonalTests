package lc.sz1288;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {
  public int leastInterval(char[] tasks, int n) {
    Map<Character, Integer> counts = new HashMap<>();
    for (char task : tasks) {
      counts.put(task, counts.getOrDefault(task, 0) + 1);
    }
    PriorityQueue<Character> q = new PriorityQueue<>(Comparator.comparingInt(counts::get).reversed());
    q.addAll(counts.keySet());

    int time = 0;
    while (!q.isEmpty()) {
      int k = n + 1;
      List<Character> temp = new LinkedList<>();
      while (k > 0 && !q.isEmpty()) {
        char top = q.poll();
        counts.put(top, counts.get(top) - 1);
        temp.add(top);
        k--;
        time++;
      }

      for (char each : temp) {
        if (counts.get(each) > 0) {
          q.add(each);
        }
      }

      if (q.isEmpty()) {
        return time;
      }
      time += k;
    }
    return time;
  }

  public static void main(String[] args) {
    TaskScheduler ts = new TaskScheduler();
    char[] tasks = {'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B'};
    System.out.println(ts.leastInterval(tasks, 2));
  }
}
