package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of edges, decide if this graph is bipartite.
 * <p>
 * Input will be in the format of: [a, b], [b, c], [d, e], ...
 * <p>
 * Vertices are named from 0 to n-1.
 * <p>
 * Follow up: what if the graph is not connected?
 */
public class BipartiteGraph {
    public boolean isBipartite(int[][] connections, int n) {
        List<List<Integer>> edges = new ArrayList<>(n);
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int[] edge : connections) {
            edges.get(edge[0]).add(edge[1]);
            edges.get(edge[1]).add(edge[0]);
        }
        int index = 0;
        while (index < n) {
            color[index] = 1;
            if (!dfs(edges, index, color)) {
                return false;
            }
            while (index < n && color[index] != 0) {
                index++;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> edges, int vertex, int[] color) {
        for (int next : edges.get(vertex)) {
            if (color[next] == 0) {
                color[next] = 3 - color[vertex];
                if (!dfs(edges, next, color)) {
                    return false;
                }
            } else if (color[next] == color[vertex]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BipartiteGraph bg = new BipartiteGraph();
        // bipartite connected graph
        System.out.println(bg.isBipartite(new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}, {2, 7}, {4, 7}, {5, 7}, {6, 7}}, 8));
        // not bipartite
        System.out.println(bg.isBipartite(new int[][]{{0, 1}, {1, 2}, {0, 2}}, 3));
        // not connected
        System.out.println(bg.isBipartite(new int[][]{{0, 1}, {1, 2}, {2, 3}, {0, 3}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 8));
    }
}
