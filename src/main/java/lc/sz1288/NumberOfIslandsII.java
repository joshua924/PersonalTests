package lc.sz1288;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example:
 * <p>
 * Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 */
public class NumberOfIslandsII {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[][] ids = new int[m][n];
        int num_islands = 0;
        int id = 0;

        for (int[] pos : positions) {
            int x = pos[0];
            int y = pos[1];
            if (ids[x][y] != 0) {
                res.add(num_islands);
                continue;
            }
            Set<Integer> islands = new HashSet<>();
            if (x > 0) {
                islands.add(ids[x - 1][y]);
            }
            if (x < m - 1) {
                islands.add(ids[x + 1][y]);
            }
            if (y > 0) {
                islands.add(ids[x][y - 1]);
            }
            if (y < n - 1) {
                islands.add(ids[x][y + 1]);
            }
            islands.remove(0);
            if (islands.size() == 0) {
                ids[x][y] = ++id;
                num_islands += 1;
                res.add(num_islands);
            } else if (islands.size() == 1) {
                ids[x][y] = islands.iterator().next();
                res.add(num_islands);
            } else {
                addToIsland(ids, x, y, m, n, ++id);
                num_islands += 1 - islands.size();
                res.add(num_islands);
            }
        }
        return res;
    }

    private void addToIsland(int[][] ids, int x, int y, int m, int n, int islandId) {
        ids[x][y] = islandId;
        for (int[] dir : DIRS) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX == m || newX < 0 || newY == n || newY < 0 || ids[newX][newY] == islandId || ids[newX][newY] == 0) {
                continue;
            }
            addToIsland(ids, newX, newY, m, n, islandId);
        }
    }

    public static void main(String[] args) {
        NumberOfIslandsII noi = new NumberOfIslandsII();
        System.out.println(noi.numIslands2(3, 3, new int[][]{{0, 1}, {1, 2}, {2, 1}, {1, 0}, {0, 2}, {0, 0}, {1, 1}}));
    }
}
