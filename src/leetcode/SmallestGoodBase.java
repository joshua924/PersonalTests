package leetcode;

import java.math.BigInteger;

public class SmallestGoodBase {
    public String smallestGoodBase(String n) {
        long num = Long.valueOf(n);
        long res = 0;
        for (int k = 60; k >= 2; k--) {
            long start = 2;
            long end = num;
            while (start < end) {
                BigInteger m = BigInteger.valueOf(start + (end - start) / 2);
                BigInteger value = m.pow(k).subtract(BigInteger.ONE);
                int compare = value.compareTo(BigInteger.valueOf(num).multiply(m.subtract(BigInteger.ONE)));
                if (compare < 0) {
                    start = m.longValue() + 1;
                } else if (compare == 0) {
                    res = m.longValue();
                    break;
                } else {
                    end = m.longValue();
                }
            }
            if (res != 0) {
                return String.valueOf(res);
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        SmallestGoodBase sgb = new SmallestGoodBase();
        System.out.println(sgb.smallestGoodBase("13"));
        System.out.println(sgb.smallestGoodBase("4681"));
    }
}
