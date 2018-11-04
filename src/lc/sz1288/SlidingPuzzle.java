package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 * <p>
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * <p>
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * <p>
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved.
 * If it is impossible for the state of the board to be solved, return -1.
 * <p>
 * Examples:
 * <p>
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 */
public class SlidingPuzzle {
    private static final int[][] DIRS = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public List<String> slidingPuzzle(int[][] board) {
        List<String> level = new ArrayList<>();
        Map<String, String> path = new HashMap<>();
        String start = toS(board);
        String end = "123450";
        path.put(start, null);
        level.add(start);
        int res = 0;
        while (!level.isEmpty()) {
            List<String> next = new ArrayList<>();
            for (String each : level) {
                if (each.equals(end)) {
                    return getPath(path, end);
                }
                int zeroIndex = each.indexOf("0");
                for (int place : DIRS[zeroIndex]) {
                    String newBoard = swap(each, zeroIndex, place);
                    if (!path.containsKey(newBoard)) {
                        next.add(newBoard);
                        path.put(newBoard, each);
                    }
                }
            }
            res++;
            level = next;
        }
        return Collections.emptyList();
    }

    private List<String> getPath(Map<String, String> path, String end) {
        LinkedList<String> res = new LinkedList<>();
        while (end != null) {
            res.push(end);
            end = path.get(end);
        }
        return res;
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
