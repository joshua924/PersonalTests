package lc.sz1288;

/**
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that
 * there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 */
public class GraphValidTree {
  public boolean validTree(int n, int[][] edges) {
    UnionFind uf = new UnionFind(n);
    for (int[] edge: edges) {
      int i = edge[0];
      int j = edge[1];
      if (uf.connected(i, j)) {
        return false;
      }
      uf.union(i, j);
    }
    return uf.getCount() == 1;
  }

  private static class UnionFind {
    private final int[] root;
    private final int[] rank;
    private int count;

    public UnionFind(int size) {
      root = new int[size];
      rank = new int[size];
      count = size;
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

    public void union(int x, int y) {
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
      }
      count--;
    }

    public boolean connected(int x, int y) {
      return find(x) == find(y);
    }

    public int getCount() {
      return count;
    }
  }
}
