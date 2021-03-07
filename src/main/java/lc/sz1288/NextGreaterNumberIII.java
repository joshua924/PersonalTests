package lc.sz1288;

import java.util.Arrays;

public class NextGreaterNumberIII {
    public int nextGreaterElement(int n) {
        char[] number = String.valueOf(n).toCharArray();
        int index = number.length - 1;
        while (index > 0 && number[index - 1] >= number[index]) {
            index--;
        }
        if (index == 0) {
            return -1;
        }
        int x = number[index - 1];
        int smallest = index;
        int j;
        for (j = index + 1; j < number.length; j++) {
            if (number[j] > x && number[j] <= number[smallest]) {
                smallest = j;
            }
        }
        swap(number, index - 1, smallest);
        Arrays.sort(number, index, number.length);
        long val = Long.parseLong(new String(number));
        return val > Integer.MAX_VALUE ? -1 : (int) val;
    }

    private void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        NextGreaterNumberIII ng = new NextGreaterNumberIII();
        System.out.println(ng.nextGreaterElement(123456));
        System.out.println(ng.nextGreaterElement(87654321));
        System.out.println(ng.nextGreaterElement(348756834));
        System.out.println(ng.nextGreaterElement(Integer.MAX_VALUE));
    }
}
