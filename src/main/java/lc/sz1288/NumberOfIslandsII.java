package lc.sz1288;

import java.util.LinkedList;
import java.util.List;

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
  private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    int[][] matrix = new int[m][n];
    UnionFind uf = new UnionFind(m * n);
    List<Integer> result = new LinkedList<>();
    int count = 0;
    for (int[] position : positions) {
      int i = position[0];
      int j = position[1];
      if (matrix[i][j] == 1) {
        continue;
      }
      matrix[i][j] = 1;
      count++;
      for (int[] dir : DIRS) {
        int x = i + dir[0];
        int y = j + dir[1];
        if (x >= m || x < 0 || y >= n || y < 0 || matrix[x][y] != 1) {
          continue;
        }
        count += uf.union(i * n + j, x * n + y);
      }
      result.add(count);
    }
    return result;
  }

  public static class UnionFind {
    private final int[] root;
    private final int[] rank;

    public UnionFind(int size) {
      root = new int[size];
      rank = new int[size];
      for (int i = 0; i < size; i++) {
        root[i] = i;
        rank[i] = 1;
      }
    }

    public int find(int x) {
      if (x == root[x]) {
        return x;
      }
      return root[x] = find(root[x]);
    }

    // return -1 if the union reduced number of islands
    public int union(int x, int y) {
      int res = 0;
      int rootX = find(x);
      int rootY = find(y);
      if (rootX != rootY) {
        if (rank[rootX] > rank[rootY]) {
          root[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
          root[rootX] = rootY;
        } else {
          root[rootY] = rootX;
          rank[rootX] += 1;
        }
        res--;
      }
      return res;
    }
  }

  public static void main(String[] args) {
    NumberOfIslandsII noi = new NumberOfIslandsII();
    System.out.println(noi.numIslands2(3, 3, new int[][]{{0, 1}, {1, 2}, {2, 1}, {1, 0}, {0, 2}, {0, 0}, {1, 1}}));
  }
}
