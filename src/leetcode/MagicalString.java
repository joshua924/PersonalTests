package leetcode;

public class MagicalString {
    public int magicalString(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] string = new int[n + 1];
        string[0] = 1;
        string[1] = 2;
        string[2] = 2;
        int head = 2, tail = 3;
        int numToBeFilled = 1;
        int sum = 1;
        while (tail < n) {
            for (int i = 0; i < string[head]; i++) {
                string[tail++] = numToBeFilled;
                if (numToBeFilled == 1 && tail <= n) {
                    sum++;
                }
            }
            numToBeFilled = 3 - numToBeFilled;
            head++;
        }
        return sum;
    }

    public static void main(String[] args) {
        MagicalString ms = new MagicalString();
        System.out.println(ms.magicalString(7));
        System.out.println(ms.magicalString(19));
        System.out.println(ms.magicalString(20));
    }
}
