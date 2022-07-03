package lc.sz1288;

import java.util.Arrays;

/**
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *
 * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
 *
 * Return the modified image after performing the flood fill.
 */
public class FloodFill {
  private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    fill(image, sr, sc, image[sr][sc], color);
    return image;
  }

  private void fill(int[][] image, int sr, int sc, int originalColor, int newColor) {
    if (image[sr][sc] != originalColor || originalColor == newColor) {
      return;
    }
    image[sr][sc] = newColor;
    for (int[] dir : DIRS) {
      int x = sr + dir[0];
      int y = sc + dir[1];
      if (x >= image.length || x < 0 || y >= image[0].length || y < 0) {
        continue;
      }
      fill(image, x, y, originalColor, newColor);
    }
  }

  public static void main(String[] args) {
    FloodFill solution = new FloodFill();
    int[][] image = {{1, 1, 1}, {1, 1, 0}, {0, 0, 1}};
    System.out.println(Arrays.deepToString(solution.floodFill(image, 1, 1, 9)));
    System.out.println(Arrays.deepToString(solution.floodFill(image, 1, 1, 0)));
  }
}
