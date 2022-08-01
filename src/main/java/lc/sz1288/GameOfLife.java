package lc.sz1288;

import java.util.Arrays;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules
 * (taken from the above Wikipedia article):
 * <p>
 * 1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * 2. Any live cell with two or three live neighbors lives on to the next generation.
 * 3. Any live cell with more than three live neighbors dies, as if by over-population..
 * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * <p>
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the
 * current state, where births and deaths occur simultaneously.
 */
public class GameOfLife {
  private static final int[][] DIRS = {
      {-1, -1},
      {-1, 0},
      {-1, 1},
      {0, -1},
      {0, 1},
      {1, -1},
      {1, 0},
      {1, 1}
  };

  public void gameOfLife(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        int count = countLiveNeighbor(board, i, j);
        if (board[i][j] == 1 && (count < 2 || count > 3)) {
          board[i][j] = 2;
        }
        if (board[i][j] == 0 && count == 3) {
          board[i][j] = 3;
        }
      }
    }
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] > 1) {
          board[i][j] -= 2;
        }
      }
    }
  }

  private int countLiveNeighbor(int[][] board, int i, int j) {
    int res = 0;
    for (int[] dir : DIRS) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
        continue;
      }
      if (board[x][y] == 1 || board[x][y] == 2) {
        res++;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    GameOfLife gol = new GameOfLife();
    int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
    gol.gameOfLife(board);
    for (int[] row : board) {
      System.out.println(Arrays.toString(row));
      System.out.println("---------------------------------");
    }
  }
}
