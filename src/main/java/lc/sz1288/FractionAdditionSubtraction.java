package lc.sz1288;

import java.util.Scanner;

public class FractionAdditionSubtraction {
    public String fractionAddition(String expression) {
        Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])");
        int A = 0, B = 1;
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            A = A * b + a * B;
            B *= b;
            int g = gcd(A, B);
            A /= g;
            B /= g;
        }
        return A + "/" + B;
    }

    private int gcd(int a, int b) {
        return a != 0 ? gcd(b % a, a) : Math.abs(b);
    }

    public static void main(String[] args) {
        FractionAdditionSubtraction fas = new FractionAdditionSubtraction();
        System.out.println(fas.fractionAddition("-1/2+1/2+1/3"));
        System.out.println(fas.fractionAddition("5/3+1/3"));
    }
}
