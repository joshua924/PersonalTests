package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class CopyWithRandomPointer {
  public Node copyRandomList(Node head) {
    return copy(head, new HashMap<>());
  }

  private Node copy(Node node, Map<Node, Node> visited) {
    if (node == null) {
      return null;
    }
    if (visited.containsKey(node)) {
      return visited.get(node);
    }
    Node newNode = new Node(node.val);
    visited.put(node, newNode);
    newNode.next = copy(node.next, visited);
    newNode.random = copy(node.random, visited);
    return newNode;
  }

  private static class Node {
    int val;
    Node next, random;

    Node(int val) {
      this.val = val;
    }

    Node(int val, Node next, Node random) {
      this.val = val;
      this.next = next;
      this.random = random;
    }
  }

  public static void main(String[] args) {
    CopyWithRandomPointer solution = new CopyWithRandomPointer();
    Node next = new Node(2, new Node(3), null);
    Node head = new Node(1, next, next);
    Node copied = solution.copyRandomList(head);
    System.out.println(copied.val);
    System.out.println(copied.next.val);
    System.out.println(copied.random.val);
  }
}

