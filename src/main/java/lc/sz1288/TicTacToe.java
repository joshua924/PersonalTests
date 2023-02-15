package lc.sz1288;

/**
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board.
 * The move is guaranteed to be a valid move.
 */
public class TicTacToe {
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

  private final int[][] board;

  public TicTacToe(int n) {
    this.board = new int[n][n];
  }

  public int move(int row, int col, int player) {
    board[row][col] = player;
    for (int i = 0; i < 4; i++) {
      int[] dir = DIRS[i];
      int[] reverse = DIRS[7 - i];
      if (count(row, col, dir, player) + count(row, col, reverse, player) - 1 == board.length) {
        return player;
      }
    }
    return 0;
  }

  private int count(int row, int col, int[] dir, int player) {
    if (row < 0 || row >= board.length || col < 0 || col >= board.length || board[row][col] != player) {
      return 0;
    }
    return 1 + count(row + dir[0], col + dir[1], dir, player);
  }

  public static void main(String[] args) {
    TicTacToe ticTacToe = new TicTacToe(3);
    System.out.println(ticTacToe.move(0, 0, 1));
    System.out.println(ticTacToe.move(0, 2, 2));
    System.out.println(ticTacToe.move(2, 2, 1));
    System.out.println(ticTacToe.move(1, 1, 2));
    System.out.println(ticTacToe.move(2, 0, 1));
    System.out.println(ticTacToe.move(1, 0, 2));
    System.out.println(ticTacToe.move(2, 1, 1));
  }
}
