package lc.sz1288;

public class ImageSmoother {
    public int[][] imageSmoother(int[][] M) {
        int[][] N = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                smooth(M, i, j, N);
            }
        }
        return N;
    }

    private void smooth(int[][] m, int i, int j, int[][] n) {
        int colorSum = 0;
        int cellSum = 0;
        if (i != 0) {
            if (j != 0) {
                cellSum++;
                colorSum += m[i - 1][j - 1];
            }
            cellSum++;
            colorSum += m[i - 1][j];
            if (j != m[0].length - 1) {
                cellSum++;
                colorSum += m[i - 1][j + 1];
            }
        }
        if (j != 0) {
            cellSum++;
            colorSum += m[i][j - 1];
        }
        cellSum++;
        colorSum += m[i][j];
        if (j != m[0].length - 1) {
            cellSum++;
            colorSum += m[i][j + 1];
        }
        if (i != m.length - 1) {
            if (j != 0) {
                cellSum++;
                colorSum += m[i + 1][j - 1];
            }
            cellSum++;
            colorSum += m[i + 1][j];
            if (j != m[0].length - 1) {
                cellSum++;
                colorSum += m[i + 1][j + 1];
            }
        }
        System.out.println(colorSum + " for " + i + "," + j);
        n[i][j] = colorSum / cellSum;
    }
}
