package lc.sz1288;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class FunctionExclusiveTimes {
  public int[] exclusiveTime(int n, List<String> logs) {
    Deque<Call> functions = new ArrayDeque<>();
    int[] res = new int[n];
    for (String log : logs) {
      String[] tokens = log.split(":");
      int name = Integer.parseInt(tokens[0]);
      int currentTime = Integer.parseInt(tokens[2]);
      if ("start".equals(tokens[1])) {
        if (!functions.isEmpty()) {
          Call last = functions.peekFirst();
          res[last.name] += currentTime - last.start;
        }
        functions.addFirst(new Call(name, currentTime));
      } else {
        currentTime++;
        Call current = functions.removeFirst();
        res[name] += currentTime - current.start;
        if (!functions.isEmpty()) {
          Call last = functions.peekFirst();
          last.start = currentTime;
        }
      }
    }
    return res;
  }

  private static class Call {
    int name;
    int start;

    Call(int name, int start) {
      this.name = name;
      this.start = start;
    }
  }

  public static void main(String[] args) {
    FunctionExclusiveTimes fet = new FunctionExclusiveTimes();
    List<String> logs = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
    System.out.println(Arrays.toString(fet.exclusiveTime(2, logs)));
  }
}
