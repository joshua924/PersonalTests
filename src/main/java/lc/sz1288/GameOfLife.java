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
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = liveNeighbors(board, i, j, m, n);
                if (board[i][j] == 1 && liveNeighbors >= 2 && liveNeighbors <= 3) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int liveNeighbors(int[][] board, int x, int y, int m, int n) {
        int count = 0;
        for (int i = Math.max(0, x - 1); i <= Math.min(m - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(n - 1, y + 1); j++) {
                if (board[i][j] > 0) {
                    count++;
                }
            }
        }
        return board[x][y] > 0 ? count - 1 : count;
    }

    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife();
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        for (int i = 0; i < 3; i++) {
            gol.gameOfLife(board);
            for (int[] row : board) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("---------------------------------");
        }
    }
}
