package lc.sz1288;

/**
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 *
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 */
public class AddString {
  public String addStrings(String num1, String num2) {
    num1 = new StringBuilder(num1).reverse().toString();
    num2 = new StringBuilder(num2).reverse().toString();
    int length = Math.max(num1.length(), num2.length());
    StringBuilder builder = new StringBuilder();

    int carry = 0;
    for (int i = 0; i < length; i++) {
      int x = 0;
      int y = 0;
      if (i < num1.length()) {
        x = num1.charAt(i) - '0';
      }
      if (i < num2.length()) {
        y = num2.charAt(i) - '0';
      }
      int digit = (x + y + carry) % 10;
      carry = (x + y + carry) / 10;
      builder.append(digit);
    }
    if (carry > 0) {
      builder.append(carry);
    }

    return builder.reverse().toString();
  }

  public static void main(String[] args) {
    AddString as = new AddString();
    System.out.println(as.addStrings("123", "45"));
    System.out.println(as.addStrings("123", "456"));
    System.out.println(as.addStrings("0", "0"));
    System.out.println(as.addStrings("0", "789"));
    System.out.println(as.addStrings("711", "789"));
  }
}
