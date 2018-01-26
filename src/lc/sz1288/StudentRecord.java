package lc.sz1288;

public class StudentRecord {
    private final static int MOD = 1000000007;

    public int checkRecord(int n) {
        int[] PorL = new int[n + 1];
        int[] P = new int[n + 1];
        PorL[0] = 1;
        P[0] = 1;
        PorL[1] = 2;
        P[1] = 1;
        for (int i = 2; i <= n; i++) {
            P[i] = PorL[i - 1];
            PorL[i] = (P[i] + P[i - 1] + P[i - 2]) & MOD;
        }
        for (int i = 0; i < n; i++) {
            int combinations = (int) ((long) PorL[i] * PorL[n - 1 - i]) % MOD;
            PorL[n] = (PorL[n] + combinations) % MOD;
        }
        return PorL[n];
    }

    public static void main(String[] args) {
        StudentRecord sr = new StudentRecord();
        System.out.println(sr.checkRecord(1));
    }
}
