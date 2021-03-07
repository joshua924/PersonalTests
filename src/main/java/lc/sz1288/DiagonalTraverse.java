package lc.sz1288;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return new int[]{};
        }
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int row = 0;
        int column = 0;
        int direction = -1;
        int i = 0;
        while (row != m - 1 || column != n - 1) {
            res[i++] = matrix[row][column];
            if (direction == -1) {
                if (column == n - 1) {
                    row++;
                    direction = -direction;
                } else if (row == 0) {
                    column++;
                    direction = -direction;
                } else {
                    row += direction;
                    column -= direction;
                }
            } else {
                if (row == m - 1) {
                    column++;
                    direction = -direction;
                } else if (column == 0) {
                    row++;
                    direction = -direction;
                } else {
                    row += direction;
                    column -= direction;
                }
            }
        }
        res[i] = matrix[m - 1][n - 1];
        return res;
    }

    public static void main(String[] args) {
        DiagonalTraverse dt = new DiagonalTraverse();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int[] diagonalOrder = dt.findDiagonalOrder(matrix);
        for (int i : diagonalOrder) {
            System.out.print(i + " ");
        }
    }
}
