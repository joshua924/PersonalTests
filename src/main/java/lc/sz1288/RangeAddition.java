package lc.sz1288;

import java.util.Arrays;

public class RangeAddition {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }
        int rowMin = Arrays.stream(ops).mapToInt(op -> op[0]).min().getAsInt();
        int columeMin = Arrays.stream(ops).mapToInt(op -> op[1]).min().getAsInt();
        return rowMin * columeMin;
    }

    public static void main(String[] args) {
        RangeAddition ra = new RangeAddition();
        int[][] ops = {{2, 2}, {3, 3}};
        System.out.println(ra.maxCount(3, 3, ops));
    }
}
