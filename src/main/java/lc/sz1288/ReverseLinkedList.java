package lc.sz1288;

import java.util.Arrays;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode reversed = new ListNode(head.val);
        ListNode next = head.next;
        while (next != null) {
            int nextVal = next.val;
            reversed = new ListNode(nextVal, reversed);
            next = next.next;
        }
        return reversed;
    }

    public ListNode reverseListInPlace(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        ListNode current = head;
        while (next != null) {
            ListNode temp = next.next;
            next.next = current;
            current = next;
            next = temp;
        }
        head.next = null;
        return current;
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();
        ListNode head = ListNode.of(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(solution.reverseList(head));
        System.out.println(solution.reverseList(null));
        System.out.println(solution.reverseListInPlace(head));
        System.out.println(solution.reverseListInPlace(null));
    }
}
