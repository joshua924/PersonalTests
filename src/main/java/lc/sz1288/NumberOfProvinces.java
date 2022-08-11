package lc.sz1288;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 */
public class NumberOfProvinces {
  public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    boolean[] visited = new boolean[n];
    int count = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        visited[i] = true;
        dfs(isConnected, visited, i);
        count++;
      }
    }
    return count;
  }

  private void dfs(int[][] isConnected, boolean[] visited, int i) {
    for (int j = 0; j < isConnected.length; j++) {
      if (isConnected[i][j] == 1 && !visited[j]) {
        visited[j] = true;
        dfs(isConnected, visited, j);
      }
    }
  }

  public static void main(String[] args) {
    NumberOfProvinces solution = new NumberOfProvinces();
    int[][] connected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    System.out.println(solution.findCircleNum(connected));
  }
}
