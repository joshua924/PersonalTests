package lc.sz1288;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * <p>
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2
 */
public class RangeSumQuery {
    // matrix[i][j] stores the sum from the original matrix[i][0], matrix[i][1] ... matrix[i][j]
    private int[][] matrix;

    public RangeSumQuery(int[][] matrix) {
        if (matrix.length == 0) {
            this.matrix = new int[0][0];
            return;
        }
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
                this.matrix[i][j] = sum;
            }
        }
    }

    public void update(int row, int col, int val) {
        int current = col == 0 ? matrix[row][col] : matrix[row][col] - matrix[row][col - 1];
        int diff = val - current;
        for (int i = col; i < matrix[row].length; i++) {
            matrix[row][i] += diff;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            sum += getRowSum(row, col1, col2);
        }
        return sum;
    }

    private int getRowSum(int row, int start, int end) {
        if (start == 0) {
            return matrix[row][end];
        } else {
            return matrix[row][end] - matrix[row][start - 1];
        }
    }
}
