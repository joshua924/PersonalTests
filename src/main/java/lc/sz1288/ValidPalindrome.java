package lc.sz1288;

/**
 * Given a non-empty string s, you may delete at most one character.
 * Judge whether you can make it a palindrome.
 */
public class ValidPalindrome {
  public boolean validPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
      }
      left++;
      right--;
    }
    return true;
  }

  private boolean isPalindrome(String s, int left, int right) {
    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  public static void main(String[] args) {
    ValidPalindrome vp = new ValidPalindrome();
    System.out.println(vp.validPalindrome("aba"));
    System.out.println(vp.validPalindrome("abc"));
    System.out.println(vp.validPalindrome("abca"));
    System.out.println(vp.validPalindrome("aaaaaaaaaaaaaaaa"));
  }
}
