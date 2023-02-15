package lc.sz1288;

/**
 * Given a non-empty string s, you may delete at most one character.
 * Judge whether you can make it a palindrome.
 */
public class ValidPalindromeII {
  public boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    s = s.toLowerCase();
    while (left < right) {
      char leftCh = s.charAt(left);
      char rightCh = s.charAt(right);
      if (!isAlphanumeric(leftCh)) {
        left++;
        continue;
      }
      if (!isAlphanumeric(rightCh)) {
        right--;
        continue;
      }
      if (leftCh != rightCh) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  private boolean isAlphanumeric(char ch) {
    return (ch <= 'Z' && ch >= 'A') || (ch <= 'z' && ch >= 'a') || (ch <= '9' && ch >= '0');
  }

  public static void main(String[] args) {
    ValidPalindromeII vp = new ValidPalindromeII();
    System.out.println(vp.isPalindrome("A man, a plan, a canal: Panama"));
    System.out.println(vp.isPalindrome("abc"));
  }
}
