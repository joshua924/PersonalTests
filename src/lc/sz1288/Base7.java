package lc.sz1288;

public class Base7 {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean sign = (num > 0);
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int digit = num % 7;
            sb.append(digit);
            num /= 7;
        }
        if (!sign) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Base7 b7 = new Base7();
        System.out.println(b7.convertToBase7(-100));
    }
}
