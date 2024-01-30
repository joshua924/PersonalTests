package lc.sz1288;

import java.util.*;

/**
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10.
 * Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z.
 * Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 * <p>
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 * <p>
 * Note:
 * <p>
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 */
public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {
        Map<Integer, Long> debt = new HashMap<>();
        for (int[] transaction : transactions) {
            debt.put(transaction[0], debt.getOrDefault(transaction[0], 0L) - transaction[2]);
            debt.put(transaction[1], debt.getOrDefault(transaction[1], 0L) + transaction[2]);
        }
        List<Long> debts = new ArrayList<>();
        for (long delta : debt.values()) {
            if (delta != 0)
                debts.add(delta);
        }
        Collections.sort(debts);
        return dfs(debts, 0, debts.size());
    }

    private int dfs(List<Long> debt, int index, int size) {
        int res = Integer.MAX_VALUE;
        Long currentVal = debt.get(index);
        while (index < size && currentVal == 0) {
            index++;
        }
        if (index == size) {
            return 0;
        }
        for (int i = index; i < size; i++) {
            if (currentVal * debt.get(i) < 0) {
                debt.set(i, debt.get(i) + currentVal);
                res = Math.min(res, 1 + dfs(debt, index + 1, size));
                debt.set(i, debt.get(i) - currentVal);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        OptimalAccountBalancing oab = new OptimalAccountBalancing();
        System.out.println(oab.minTransfers(new int[][]{{0, 1, 10}, {2, 0, 5}}));
    }
}
