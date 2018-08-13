package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

public class MaximumSwap {
    public int maximumSwap(int num) {
        List<Integer> list = fromNum(num);
        int max = num;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                swap(list, i, j);
                max = Math.max(max, toNum(list));
                swap(list, i, j);
            }
        }
        return max;
    }

    private List<Integer> fromNum(int num) {
        List<Integer> res = new ArrayList<>();
        while (num > 0) {
            res.add(0, num % 10);
            num /= 10;
        }
        return res;
    }

    private int toNum(List<Integer> list) {
        int num = 0;
        for (int each : list) {
            num *= 10;
            num += each;
        }
        return num;
    }

    private void swap(List<Integer> list, int id1, int id2) {
        int tmp = list.get(id1);
        list.set(id1, list.get(id2));
        list.set(id2, tmp);
    }

    public static void main(String[] args) {
        MaximumSwap ms = new MaximumSwap();
        System.out.println(ms.maximumSwap(9932));
        System.out.println(ms.maximumSwap(2736));
        System.out.println(ms.maximumSwap(0));
        System.out.println(ms.maximumSwap(5));
    }
}
