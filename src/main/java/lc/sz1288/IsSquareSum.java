package lc.sz1288;

public class IsSquareSum {
    public boolean judgeSquareSum(int c) {
        if (c <= 2 || isSquare(c)) {
            return true;
        }
        int sqrt = (int) Math.sqrt(c) + 1;
        for (int i = 1; i < sqrt; i++) {
            if (isSquare(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    public static void main(String[] args) {
        IsSquareSum iss = new IsSquareSum();
        System.out.println(iss.judgeSquareSum(327452635));
    }
}
