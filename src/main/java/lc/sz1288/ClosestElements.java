package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = Arrays.binarySearch(arr, x);
        if (index < 0) {
            index = -(index + 1);
        }
        int head = index - 1;
        int tail = index;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        while (tail - head <= k) {
            if (head == -1) {
                right.add(arr[tail++]);
            } else if (tail == arr.length) {
                left.add(0, arr[head--]);
            } else if (x - arr[head] <= arr[tail] - x) {
                left.add(0, arr[head--]);
            } else if (arr[tail] - x < x - arr[head]) {
                right.add(arr[tail++]);
            }
        }
        left.addAll(right);
        return left;
    }

    public static void main(String[] args) {
        ClosestElements ce = new ClosestElements();
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(ce.findClosestElements(arr, 4, 3));
        arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(ce.findClosestElements(arr, 4, -1));
        arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(ce.findClosestElements(arr, 4, 5));
        arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(ce.findClosestElements(arr, 4, 8));
    }
}
