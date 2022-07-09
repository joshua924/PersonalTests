package lc.sz1288;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Node {
  int val;
  List<Node> children;

  public Node(int val) {
    this.val = val;
    this.children = new ArrayList<>();
  }

  @Override
  public String toString() {
    return "Node{" +
        "val=" + val +
        ", children=" + children.size() +
        '}';
  }
}
