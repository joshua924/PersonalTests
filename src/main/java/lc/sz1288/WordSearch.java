package lc.sz1288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 *
 * The same letter cell may not be used more than once.
 */
public class WordSearch {
  private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  public boolean exist(char[][] board, String word) {
    if (word.isEmpty()) {
      return true;
    }
    Map<Character, Set<String>> charPositions = new HashMap<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        char ch = board[i][j];
        if (charPositions.containsKey(ch)) {
          charPositions
              .get(ch)
              .add(i + "," + j);
        } else {
          Set<String> positions = new HashSet<>();
          positions.add(i + "," + j);
          charPositions.put(ch, positions);
        }
      }
    }

    boolean[][] inPath = new boolean[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] != word.charAt(0)) {
          continue;
        }
        if (dfs(board, i, j, word, 0, inPath, charPositions)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] inPath, Map<Character, Set<String>> charPositions) {
    if (index == word.length() - 1) {
      return true;
    }
    inPath[i][j] = true;
    for (int[] dir : DIRS) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || inPath[x][y]) {
        continue;
      }
      if (!charPositions.getOrDefault(word.charAt(index + 1), new HashSet<>()).contains(x + "," + y)) {
        continue;
      }
      if (dfs(board, x, y, word, index + 1, inPath, charPositions)) {
        return true;
      }
    }
    inPath[i][j] = false;
    return false;
  }

  public static void main(String[] args) {
    WordSearch ws = new WordSearch();
    char[][] board = {
        {'A', 'B', 'C', 'E'},
        {'S', 'F', 'E', 'S'},
        {'A', 'D', 'E', 'E'}};
    System.out.println(ws.exist(board, "ABCCED"));
    System.out.println(ws.exist(board, "SEE"));
    System.out.println(ws.exist(board, "ABCESEEEFS"));
  }
}
