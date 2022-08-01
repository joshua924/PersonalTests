package lc.sz1288;

/**
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined by
 * its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2)
 * Returns the sum of the elements of matrix inside the rectangle defined by
 * its upper left corner (row1, col1) and lower right corner (row2, col2).
 */
public class NumMatrix {
  private final int[][] sums;
  private final int m;
  private final int n;

  public NumMatrix(int[][] matrix) {
    this.m = matrix.length;
    this.n = matrix[0].length;
    this.sums = new int[m][n];
    fillSums(matrix);
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    int sum = sums[row2][col2];
    if (row1 > 0) {
      sum -= sums[row1 - 1][col2];
    }
    if (col1 > 0) {
      sum -= sums[row2][col1 - 1];
    }
    if (row1 > 0 && col1 > 0) {
      sum += sums[row1 - 1][col1 - 1];
    }
    return sum;
  }

  private void fillSums(int[][] matrix) {
    int sum = 0;
    for (int i = 0; i < m; i++) {
      sum += matrix[i][0];
      sums[i][0] = sum;
    }
    sum = 0;
    for (int j = 0; j < n; j++) {
      sum += matrix[0][j];
      sums[0][j] = sum;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + matrix[i][j];
      }
    }
  }
}
