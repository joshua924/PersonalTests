package lc.sz1288;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlidingPuzzle {
    private static final int[][] DIRS = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        Set<String> seen = new HashSet<>();
        List<String> level = new ArrayList<>();
        String start = toS(board);
        String end = "123450";
        seen.add(start);
        level.add(start);
        int res = 0;
        while (!level.isEmpty()) {
            List<String> next = new ArrayList<>();
            for (String each : level) {
                if (each.equals(end)) {
                    return res;
                }
                int zeroIndex = each.indexOf("0");
                for (int place : DIRS[zeroIndex]) {
                    String newBoard = swap(each, zeroIndex, place);
                    if (seen.add(newBoard)) {
                        next.add(newBoard);
                    }
                }
            }
            res++;
            level = next;
        }
        return -1;
    }

    private String swap(String board, int i, int j) {
        StringBuilder sb = new StringBuilder(board);
        sb.setCharAt(i, board.charAt(j));
        sb.setCharAt(j, board.charAt(i));
        return sb.toString();
    }

    private String toS(int[][] board) {
        return "" + board[0][0] + board[0][1] + board[0][2] + board[1][0] + board[1][1] + board[1][2];
    }

    public static void main(String[] args) {
        SlidingPuzzle sp = new SlidingPuzzle();
        System.out.println(sp.slidingPuzzle(new int[][]{{1, 2, 3}, {5, 4, 0}}));
        System.out.println(sp.slidingPuzzle(new int[][]{{4, 1, 2}, {5, 0, 3}}));
        System.out.println(sp.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 5, 0}}));
    }
}
