package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
 * <p>
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 * <p>
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first.
 * And after cutting, the original place has the tree will become a grass (value 1).
 * <p>
 * You will start from the point (0, 0) and you should output the minimum bfs you need to walk to cut off all the trees.
 * If you can't cut off all the trees, output -1 in that situation.
 * <p>
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 */
public class CutOffTrees {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) != 0) {
                    trees.add(new int[]{i, j});
                }
            }
        }
        trees.sort(Comparator.comparingInt(tree -> forest.get(tree[0]).get(tree[1])));
        int[] position = new int[]{0, 0};
        int steps = 0;
        for (int[] tree : trees) {
            int step = bfs(forest, position[0], position[1], tree[0], tree[1]);
            if (step < 0) {
                return -1;
            }
            steps += step;
            position = tree;
        }
        return steps;
    }

    private int bfs(List<List<Integer>> forest, int x, int y, int targetX, int targetY) {
        if (x == targetX && y == targetY) {
            return 0;
        }
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        int steps = 0;
        List<int[]> positions = Arrays.asList(new int[]{x, y});
        visited[x][y] = true;
        while (!positions.isEmpty()) {
            steps++;
            List<int[]> nextPositions = new ArrayList<>();
            for (int[] each : positions) {
                for (int[] dir : DIRS) {
                    int newX = each[0] + dir[0];
                    int newY = each[1] + dir[1];
                    if (newX >= 0 && newX < forest.size() && newY >= 0 && newY < forest.get(0).size() && forest.get(newX).get(newY) > 0 && !visited[newX][newY]) {
                        if (newX == targetX && newY == targetY) {
                            return steps;
                        }
                        visited[newX][newY] = true;
                        nextPositions.add(new int[]{newX, newY});
                    }
                }
            }
            positions = nextPositions;
        }
        return -1;
    }

    public static void main(String[] args) {
        CutOffTrees cot = new CutOffTrees();
        System.out.println(cot.cutOffTree(Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(0, 0, 0),
                Arrays.asList(7, 6, 5)
        )));
        System.out.println(cot.cutOffTree(Arrays.asList(
                Arrays.asList(2, 3, 4),
                Arrays.asList(0, 0, 5),
                Arrays.asList(6, 7, 8)
        )));
    }
}
