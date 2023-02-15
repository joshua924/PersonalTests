package lc.sz1288;

import java.util.Arrays;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 */
public class SetMatrixZeroes {
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    boolean fistRowZero = false;
    boolean firstColumnZero = false;

    for (int i = 0; i < m; i++) {
      if (matrix[i][0] == 0) {
        fistRowZero = true;
        break;
      }
    }
    for (int j = 0; j < n; j++) {
      if (matrix[0][j] == 0) {
        firstColumnZero = true;
        break;
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    for (int i = 1; i < m; i++) {
      if (matrix[i][0] == 0) {
        for (int j = 1; j < n; j++) {
          matrix[i][j] = 0;
        }
      }
    }
    for (int j = 1; j < n; j++) {
      if (matrix[0][j] == 0) {
        for (int i = 1; i < m; i++) {
          matrix[i][j] = 0;
        }
      }
    }

    if (fistRowZero) {
      for (int i = 0; i < m; i++) {
        matrix[i][0] = 0;
      }
    }
    if (firstColumnZero) {
      for (int j = 0; j < n; j++) {
        matrix[0][j] = 0;
      }
    }
  }

  public static void main(String[] args) {
    SetMatrixZeroes solution = new SetMatrixZeroes();
    int[][] matrix = {{1,0,3}};
    solution.setZeroes(matrix);
    System.out.println(Arrays.deepToString(matrix));
  }
}
