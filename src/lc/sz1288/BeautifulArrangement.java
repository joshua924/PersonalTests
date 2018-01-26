package lc.sz1288;

public class BeautifulArrangement {
    public int countArrangement(int N) {
        boolean[] used = new boolean[N + 1];
        int[] res = new int[1];
        backtrack(1, used, N, res);
        return res[0];
    }

    private void backtrack(int index, boolean[] used, int n, int[] res) {
        if (index == n + 1) {
            res[0]++;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (i % index == 0 || index % i == 0) {
                used[i] = true;
                backtrack(index + 1, used, n, res);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        BeautifulArrangement ba = new BeautifulArrangement();
        System.out.println(ba.countArrangement(1));
        System.out.println(ba.countArrangement(2));
        System.out.println(ba.countArrangement(3));
        System.out.println(ba.countArrangement(4));
        System.out.println(ba.countArrangement(5));
        System.out.println(ba.countArrangement(6));
        System.out.println(ba.countArrangement(7));
        System.out.println(ba.countArrangement(8));
    }
}
