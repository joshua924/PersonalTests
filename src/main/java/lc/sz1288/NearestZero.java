package lc.sz1288;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class NearestZero {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Set<int[]> set = new HashSet<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    set.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int distance = 0;
        while (!set.isEmpty()) {
            Set<int[]> toAdd = new HashSet<>();
            for (int[] current : set) {
                int i = current[0];
                int j = current[1];
                matrix[i][j] = distance;
                for (int[] dir : DIRS) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && !visited[newI][newJ]) {
                        toAdd.add(new int[]{newI, newJ});
                        visited[newI][newJ] = true;
                    }
                }
            }
            distance++;
            set = toAdd;
        }
        return matrix;
    }

    public static void main(String[] args) {
        NearestZero nz = new NearestZero();
        int[][] matrix = {{0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] updateMatrix = nz.updateMatrix(matrix);
        for (int[] ints : updateMatrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
