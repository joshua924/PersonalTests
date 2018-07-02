package lc.sz1288;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FunctionExclusiveTimes {
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<NameAndStartTime> executingFunctions = new Stack<>();
        int[] res = new int[n];
        for (String log : logs) {
            String[] tokens = log.split(":");
            int name = Integer.parseInt(tokens[0]);
            int currentTime = Integer.parseInt(tokens[2]);
            if (tokens[1].equals("start")) {
                if (!executingFunctions.isEmpty()) {
                    NameAndStartTime previous = executingFunctions.peek();
                    res[previous.name] += currentTime - previous.startTime;
                }
                executingFunctions.push(new NameAndStartTime(name, currentTime));
            } else {
                NameAndStartTime nameAndStartTime = executingFunctions.pop();
                res[nameAndStartTime.name] += currentTime - nameAndStartTime.startTime + 1;
                if (!executingFunctions.isEmpty()) {
                    NameAndStartTime previous = executingFunctions.peek();
                    previous.startTime = currentTime + 1;
                }
            }
        }
        return res;
    }

    private static class NameAndStartTime {
        int name;
        int startTime;

        public NameAndStartTime(int name, int startTime) {
            this.name = name;
            this.startTime = startTime;
        }
    }

    public static void main(String[] args) {
        FunctionExclusiveTimes fet = new FunctionExclusiveTimes();
        List<String> logs = Arrays.asList("0:start:0", "1:start:5", "2:start:6", "3:start:9", "4:start:11", "5:start:12", "6:start:14", "7:start:15", "1:start:24", "1:end:29", "7:end:34",
                "6:end:37", "5:end:39", "4:end:40", "3:end:45", "0:start:49", "0:end:54", "5:start:55", "5:end:59", "4:start:63", "4:end:66", "2:start:69", "2:end:70", "2:start:74", "6:start:78",
                "0:start:79", "0:end:80", "6:end:85", "1:start:89", "1:end:93", "2:end:96", "2:end:100", "1:end:102", "2:start:105", "2:end:109", "0:end:114");
        System.out.println(Arrays.toString(fet.exclusiveTime(8, logs)));
    }
}
