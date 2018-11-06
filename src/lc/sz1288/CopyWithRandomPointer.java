package lc.sz1288;

public class CopyWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode res = new RandomListNode(head.label);
        RandomListNode current = res;
        while (current != null) {
            copy(head, current);
            head = head.next;
            current = current.next;
        }
        return res;
    }

    private void copy(RandomListNode head, RandomListNode current) {
        current.next = head.next == null ? null : new RandomListNode(head.next.label);
        current.random = head.random == null ? null : new RandomListNode(head.random.label);
    }

    public class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }
}

