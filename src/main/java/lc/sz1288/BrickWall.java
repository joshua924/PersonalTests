package lc.sz1288;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> intervalCount = new HashMap<>();
        for (List<Integer> row : wall) {
            int width = 0;
            for (int brick : row) {
                width += brick;
                intervalCount.put(width, intervalCount.getOrDefault(width, 0) + 1);
            }
            intervalCount.remove(width);
        }
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : intervalCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }
        return wall.size() - maxCount;
    }

    public static void main(String[] rags) {
        BrickWall bw = new BrickWall();
        System.out.println(bw.leastBricks(Arrays.asList(
                Arrays.asList(1, 2, 2, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 4),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 1, 1)
        )));
    }
}
