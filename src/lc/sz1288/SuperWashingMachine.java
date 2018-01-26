package lc.sz1288;

import java.util.stream.IntStream;

public class SuperWashingMachine {
    public int findMinMoves(int[] machines) {
        int sum = IntStream.of(machines).sum();
        if (sum % machines.length != 0) {
            return -1;
        }
        int target = sum / machines.length;
        int current = 0;
        int minMove = 0;
        for (int machine : machines) {
            minMove = Math.max(minMove, machine - target);
            minMove = Math.max(minMove, Math.abs(current));
            current += machine - target;
        }
        return minMove;
    }

    public static void main(String[] args) {
        SuperWashingMachine swm = new SuperWashingMachine();
        System.out.println(swm.findMinMoves(new int[]{0, 3, 0}));
        System.out.println(swm.findMinMoves(new int[]{0, 4, 5}));
        System.out.println(swm.findMinMoves(new int[]{1, 0, 5}));
        System.out.println(swm.findMinMoves(new int[]{0, 0, 0, 4}));
        System.out.println(swm.findMinMoves(new int[]{9, 1, 8, 8, 9}));
    }
}
