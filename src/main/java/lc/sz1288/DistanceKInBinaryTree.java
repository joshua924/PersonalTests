package lc.sz1288;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given the root of a binary tree, the value of a target node target, and an integer k,
 * return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 */
public class DistanceKInBinaryTree {
  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
    fillGraph(root, graph);

    Set<TreeNode> visited = new HashSet<>();
    List<TreeNode> current = new ArrayList<>();
    current.add(target);
    visited.add(target);
    int step = 0;
    while (step < k) {
      List<TreeNode> next = new ArrayList<>();
      for (TreeNode each : current) {
        List<TreeNode> neighbors = graph.get(each);
        for (TreeNode neighbor : neighbors) {
          if (visited.contains(neighbor)) {
            continue;
          }
          visited.add(neighbor);
          next.add(neighbor);
        }
      }
      current = next;
      step++;
    }
    return current
        .stream()
        .map(n -> n.val)
        .collect(Collectors.toList());
  }

  private void fillGraph(TreeNode node, Map<TreeNode, List<TreeNode>> graph) {
    if (!graph.containsKey(node)) {
      graph.put(node, new ArrayList<>());
    }
    if (node.left != null) {
      graph
          .get(node)
          .add(node.left);
      if (!graph.containsKey(node.left)) {
        graph.put(node.left, new ArrayList<>());
      }
      graph
          .get(node.left)
          .add(node);
      fillGraph(node.left, graph);
    }
    if (node.right != null) {
      graph
          .get(node)
          .add(node.right);
      if (!graph.containsKey(node.right)) {
        graph.put(node.right, new ArrayList<>());
      }
      graph
          .get(node.right)
          .add(node);
      fillGraph(node.right, graph);
    }
  }

  public static void main(String[] args) {
    DistanceKInBinaryTree solution = new DistanceKInBinaryTree();
    TreeNode node2 = new TreeNode(2, new TreeNode(7), new TreeNode(4));
    TreeNode left = new TreeNode(5, new TreeNode(6), node2);
    TreeNode right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
    TreeNode root = new TreeNode(3, left, right);
    System.out.println(solution.distanceK(root, left, 2));
  }
}
