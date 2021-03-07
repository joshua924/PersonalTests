package lc.sz1288;

import lombok.ToString;

import java.util.List;

@ToString(exclude = {"next"})
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    static ListNode of(List<Integer> list) {
        ListNode root = new ListNode(list.get(0));
        ListNode current = root;
        for (int i = 1; i < list.size(); i++) {
            current.next = new ListNode(list.get(i));
            current = current.next;
        }
        return root;
    }
}
