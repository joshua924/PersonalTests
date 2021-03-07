package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * On a 2x4 board, there are 7 tiles represented by the integers 1 through 7, and an empty square represented by 0.
 * <p>
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * <p>
 * The state of the board is solved if and only if the board is [[1,2,3,4],[5,6,7,0]].
 * <p>
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved.
 * If it is impossible for the state of the board to be solved, return -1.
 * <p>
 * Examples:
 * <p>
 * Input: board = [[1,2,3,4],[5,6,0,7]]
 * Output: 1
 * Explanation: Swap the 0 and the 7 in one move.
 */
public class SlidingPuzzle {
    private static final int[][] DIRS = new int[][]{{1, 4}, {0, 2, 5}, {1, 3, 6}, {2, 7}, {0, 5}, {1, 4, 6}, {2, 5, 7}, {3, 6}};

    public List<String> slidingPuzzle(int[][] board) {
        List<String> level = new ArrayList<>();
        Map<String, String> visited = new HashMap<>();
        String start = toS(board);
        String end = "12345670";
        visited.put(start, null);
        level.add(start);
        int res = 0;
        while (!level.isEmpty()) {
            List<String> next = new ArrayList<>();
            for (String current : level) {
                if (current.equals(end)) {
                    return constructPath(visited, end);
                }
                int zeroIndex = current.indexOf('0');
                for (int place : DIRS[zeroIndex]) {
                    String newBoard = swap(current, zeroIndex, place);
                    if (!visited.containsKey(newBoard)) {
                        visited.put(newBoard, current);
                        next.add(newBoard);
                    }
                }
            }
            res++;
            level = next;
        }
        return Collections.emptyList();
    }

    private String swap(String board, int i, int j) {
        StringBuilder sb = new StringBuilder(board);
        sb.setCharAt(i, board.charAt(j));
        sb.setCharAt(j, board.charAt(i));
        return sb.toString();
    }

    private String toS(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell);
            }
        }
        return sb.toString();
    }

    private List<String> constructPath(Map<String, String> path, String end) {
        LinkedList<String> res = new LinkedList<>();
        while (end != null) {
            res.push(end);
            end = path.get(end);
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingPuzzle sp = new SlidingPuzzle();
        System.out.println(sp.slidingPuzzle(new int[][]{{1, 2, 3, 4}, {7, 6, 5, 0}}));
        System.out.println(sp.slidingPuzzle(new int[][]{{1, 2, 3, 4}, {5, 6, 0, 7}}));
        System.out.println(sp.slidingPuzzle(new int[][]{{1, 2, 3, 4}, {0, 5, 6, 7}}));
    }
}
