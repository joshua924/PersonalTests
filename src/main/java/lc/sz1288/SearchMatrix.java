package lc.sz1288;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */
public class SearchMatrix {
  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;
    int i = m - 1;
    int j = 0;
    while (i >= 0 && j < n) {
      if (matrix[i][j] == target) {
        return true;
      }
      if (matrix[i][j] < target) {
        j++;
      } else {
        i--;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    SearchMatrix solution = new SearchMatrix();
    int[][] matrix = {{1, 3}, {2, 5}};
    System.out.println(solution.searchMatrix(matrix, 4));
    System.out.println(solution.searchMatrix(matrix, 3));
  }
}
