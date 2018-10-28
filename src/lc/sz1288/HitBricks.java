package lc.sz1288;

import java.util.Arrays;

/**
 * We have a grid of 1s and 0s; the 1s in a cell represent bricks. A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent
 * bricks will not drop.
 * <p>
 * We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop
 * because of that erasure.
 * <p>
 * Return an array representing the number of bricks that will drop after each erasure in sequence.
 */
public class HitBricks {
    private static final int[][] DIRS = {{1, 0}, {0, -1}, {0, 1}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] res = new int[hits.length];
        int hold = dfs(grid);
        for (int i = 0; i < hits.length; i++) {
            int[] hit = hits[i];
            grid[hit[0]][hit[1]] = 0;
            int newHold = dfs(grid);
            res[i] = hold == newHold ? 0 : hold - newHold - 1;
            hold = newHold;
        }
        return res;
    }

    private int dfs(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                res += dfs(grid, 0, i, visited);
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        int count = 1;
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            count += dfs(grid, i + dir[0], j + dir[1], visited);
        }
        return count;
    }

    public static void main(String[] args) {
        HitBricks hb = new HitBricks();
        int[][] grid = {{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = {{1, 0}};
        System.out.println(Arrays.toString(hb.hitBricks(grid, hits)));
        grid = new int[][]{{1, 0, 0, 0}, {1, 1, 0, 0}};
        hits = new int[][]{{1, 1}, {1, 0}};
        System.out.println(Arrays.toString(hb.hitBricks(grid, hits)));
    }
}
