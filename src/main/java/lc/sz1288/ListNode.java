package lc.sz1288;

import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    static ListNode of(List<Integer> list) {
        if (list.isEmpty()) {
            return null;
        }
        ListNode root = new ListNode(list.get(0));
        ListNode current = root;
        for (int i = 1; i < list.size(); i++) {
            current.next = new ListNode(list.get(i));
            current = current.next;
        }
        return root;
    }

    @Override
    public String toString() {
        if (next == null) {
            return val + "";
        }
        return val + ", " + next;
    }
}
