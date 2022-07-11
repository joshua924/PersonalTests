package lc.sz1288;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 */
public class VerticalOrderTraversal {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Map<Integer, List<Integer>> nodeMap = new HashMap<>();
    LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();

    queue.offer(new Pair<>(root, 0));
    int minColumn = 0;
    int maxColumn = 0;
    while (!queue.isEmpty()) {
      Pair<TreeNode, Integer> pair = queue.pollFirst();
      TreeNode node = pair.first;
      Integer column = pair.second;
      if (node == null) {
        continue;
      }
      minColumn = Integer.min(minColumn, column);
      maxColumn = Integer.max(maxColumn, column);
      if (!nodeMap.containsKey(column)) {
        nodeMap.put(column, new ArrayList<>());
      }
      nodeMap.get(column).add(node.val);
      queue.addLast(new Pair<>(node.left, column - 1));
      queue.addLast(new Pair<>(node.right, column + 1));
    }

    for (int i = minColumn; i <= maxColumn; i++) {
      result.add(nodeMap.get(i));
    }
    return result;
  }

  private static class Pair<T,V> {
    T first;
    V second;

    Pair(T first, V second) {
      this.first = first;
      this.second = second;
    }
  }

  public static void main(String[] args) {
    VerticalOrderTraversal solution = new VerticalOrderTraversal();
    TreeNode left = new TreeNode(
        9,
        new TreeNode(4),
        new TreeNode(0, null, new TreeNode(2)));
    TreeNode right = new TreeNode(
        8,
        new TreeNode(1, new TreeNode(5), null),
        new TreeNode(7)
    );
    TreeNode root = new TreeNode(3, left, right);
    System.out.println(solution.verticalOrder(root));
  }
}
