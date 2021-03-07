package lc.sz1288;

public class FriendCycle {
    public int findCircleNum(int[][] M) {
        int len = M.length;
        UnionFind uf = UnionFind.instance(len);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
}
