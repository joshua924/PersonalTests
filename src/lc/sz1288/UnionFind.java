package lc.sz1288;

public class UnionFind {
    private int[] parent;
    private int count;

    public static UnionFind instance(int n) {
        return new UnionFind(n);
    }

    private UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        count--;
        parent[rootQ] = rootP;
    }

    public int getCount() {
        return count;
    }
}
