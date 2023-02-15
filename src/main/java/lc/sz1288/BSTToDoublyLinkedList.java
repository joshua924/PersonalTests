package lc.sz1288;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
 * For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor,
 * and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 */
public class BSTToDoublyLinkedList {
  private Node first = null;
  private Node last = null;

  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return null;
    }
    toDoublyList(root);
    last.right = first;
    first.left = last;
    return first;
  }

  private void toDoublyList(Node node) {
    if (node == null) {
      return;
    }
    toDoublyList(node.left);
    if (last != null) {
      last.right = node;
      node.left = last;
    } else {
      first = node;
    }
    last = node;
    toDoublyList(node.right);
  }

  private static class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
      val = _val;
      left = _left;
      right = _right;
    }

    @Override
    public String toString() {
      Node current = this;
      StringBuilder string = new StringBuilder();
      do {
        string.append(current.val).append(", ");
        current = current.right;
      } while (current != this);
      return string.toString();
    }
  }

  public static void main(String[] args) {
    BSTToDoublyLinkedList solution = new BSTToDoublyLinkedList();
    Node left = new Node(2, new Node(1), new Node(3));
    Node root = new Node(4, left, new Node(5));
    System.out.println(solution.treeToDoublyList(root));
  }
}
