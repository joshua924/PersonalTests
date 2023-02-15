package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }
    return cloneNode(node, new HashMap<>());
  }

  private Node cloneNode(Node oldNode, Map<Integer, Node> copied) {
    Node newNode = new Node(oldNode.val);
    copied.put(newNode.val, newNode);
    for (Node neighbor : oldNode.children) {
      if (copied.containsKey(neighbor.val)) {
        newNode.children.add(copied.get(neighbor.val));
      } else {
        Node newNeighbor = cloneNode(neighbor, copied);
        newNode.children.add(newNeighbor);
      }
    }
    return newNode;
  }

  public static void main(String[] args) {
    CloneGraph solution = new CloneGraph();
    System.out.println(solution.cloneGraph(new Node(1)));
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    node1.children.add(node2);
    node2.children.add(node3);
    node3.children.add(node1);
    System.out.println(solution.cloneGraph(node1));
  }
}
