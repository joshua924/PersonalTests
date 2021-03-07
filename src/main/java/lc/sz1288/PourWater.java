package lc.sz1288;

import java.util.Arrays;

/**
 * We are given an elevation map, heights[i] representing the height of the terrain at that index.
 * The width at each index is 1. After V units of water fall at index K, how much water is at each index?
 * <p>
 * Water first drops at index K and rests on top of the highest terrain or water at that index. Then, it flows according to the following rules:
 * <p>
 * If the droplet would eventually fall by moving left, then move left.
 * Otherwise, if the droplet would eventually fall by moving right, then move right.
 * Otherwise, rise at it's current position.
 * <p>
 * Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction.
 * Also, "level" means the height of the terrain plus any water in that column.
 * We can assume there's infinitely high terrain on the two sides out of bounds of the array.
 * Also, there could not be partial water being spread out evenly on more than 1 grid block - each unit of water has to be in exactly one block.
 */
public class PourWater {
    public int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            int drop = K;
            for (int index = K - 1; index >= 0 && heights[index] <= heights[index + 1]; index--) {
                if (heights[index] < heights[index + 1]) {
                    drop = index;
                }
            }
            if (drop != K) {
                heights[drop]++;
                continue;
            }
            drop = K;
            for (int index = K + 1; index < heights.length && heights[index] <= heights[index - 1]; index++) {
                if (heights[index] < heights[index - 1]) {
                    drop = index;
                }
            }
            heights[drop]++;
        }
        return heights;
    }

    public int[] pourWater_noWall(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            int drop = K;
            boolean flowOut = true;
            for (int index = K - 1; index >= 0 && heights[index] <= heights[index + 1]; index--) {
                if (heights[index] < heights[index + 1]) {
                    drop = index;
                }
            }
            for (int index = K - 1; index >= 0; index--) {
                if (heights[index] > heights[index + 1]) {
                    flowOut = false;
                    break;
                }
            }
            if (flowOut) {
                continue;
            }
            heights[drop]++;
        }
        print(heights);
        return heights;
    }

    public void print(int[] heights) {
        int row = 0;
        for (int h : heights) {
            row = Math.max(row, h);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = row; i >= 1; i--) {
            for (int j = 0; j < heights.length; j++) {
                if (heights[j] < i) {
                    sb.append("  ");
                } else {
                    sb.append("# ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        PourWater pw = new PourWater();
        System.out.println(Arrays.toString(pw.pourWater(new int[]{2, 1, 1, 2, 1, 2, 2}, 4, 3)));
        System.out.println(Arrays.toString(pw.pourWater(new int[]{2, 1, 1, 2, 1, 2, 2}, 8, 1)));
        System.out.println(Arrays.toString(pw.pourWater_noWall(new int[]{2, 1, 1, 2, 1, 2, 2}, 4, 3)));
        System.out.println(Arrays.toString(pw.pourWater_noWall(new int[]{2, 1, 1, 2, 1, 2, 2}, 8, 1)));
    }
}
