package lc.sz1288;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparing(l -> l.val));
        for (ListNode ln : lists) {
            if (ln != null) {
                pq.offer(ln);
            }
        }
        ListNode result = new ListNode(0);
        ListNode current = result;
        while (!pq.isEmpty()) {
            ListNode listNode = pq.poll();
            ListNode next = new ListNode(listNode.val);
            current.next = next;
            current = next;
            if (listNode.next != null) {
                pq.offer(listNode.next);
            }
        }
        return result.next;
    }

    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();
        System.out.println(solution.mergeKLists(
                new ListNode[]{
                        ListNode.of(Arrays.asList(1, 4, 5)),
                        ListNode.of(Arrays.asList(1, 3, 4)),
                        null,
                        ListNode.of(Arrays.asList(2, 6)),
                        ListNode.of(Collections.singletonList(-10))
                }
        ));
    }
}
