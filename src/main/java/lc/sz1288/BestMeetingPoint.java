package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 * The distance is calculated using Manhattan Distance, where distance (p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * <p>
 * Output: 6
 * <p>
 * Explanation: Given three people living at (0,0), (0,4), and (2,2):
 * The point (0,2) is an ideal meeting point, as the total travel distance
 * of 2+2+2=6 is minimal. So return 6.
 */
public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                    cols.add(col);
                }
            }
        }
        int row = rows.get(rows.size() / 2);
        Collections.sort(cols);
        int col = cols.get(cols.size() / 2);
        return minDistance(rows, row) + minDistance(cols, col);
    }

    private int minDistance(List<Integer> points, int origin) {
        int distance = 0;
        for (int point : points) {
            distance += Math.abs(point - origin);
        }
        return distance;
    }

    public static void main(String[] args) {
        BestMeetingPoint bmp = new BestMeetingPoint();
        System.out.println(bmp.minTotalDistance(new int[][]{{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}}));
    }
}
