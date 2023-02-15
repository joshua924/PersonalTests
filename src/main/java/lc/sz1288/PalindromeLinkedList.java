package lc.sz1288;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 */
public class PalindromeLinkedList {
  public boolean isPalindrome(ListNode head) {
    int size = 0;
    ListNode current = head;
    while (current != null) {
      size++;
      current = current.next;
    }

    // reverse the first half
    int mid = size / 2;
    ListNode left = head;
    ListNode pre = null;
    ListNode right = head;
    for (int i = 0; i < mid; i++) {
      left = right;
      right = left.next;
      left.next = pre;
      pre = left;
    }

    // compare first half with second half
    if (size % 2 == 1) {
      right = right.next;
    }
    while (right != null) {
      if (left.val != right.val) {
        return false;
      }
      left = left.next;
      right = right.next;
    }
    return true;
  }
}
