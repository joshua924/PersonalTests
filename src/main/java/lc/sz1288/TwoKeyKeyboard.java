package lc.sz1288;

public class TwoKeyKeyboard {
    public int minSteps(int n) {
        int[] minStep = new int[]{Integer.MAX_VALUE};
        backtrack(n, 1, 0, 0, minStep);
        return minStep[0];
    }

    private void backtrack(int n, int current, int copied, int step, int[] steps) {
        if (current > n) {
            return;
        }
        if (current == n) {
            steps[0] = Math.min(steps[0], step);
            return;
        }
        if (copied != current) {
            backtrack(n, current, current, step + 1, steps);
        }
        if (copied != 0) {
            backtrack(n, current + copied, copied, step + 1, steps);
        }
    }

    public static void main(String[] args) {
        TwoKeyKeyboard tkk = new TwoKeyKeyboard();
        System.out.println(tkk.minSteps(1));
        System.out.println(tkk.minSteps(3));
        System.out.println(tkk.minSteps(20));
        System.out.println(tkk.minSteps(100));
        System.out.println(tkk.minSteps(999));
    }
}
