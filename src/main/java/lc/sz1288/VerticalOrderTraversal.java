package lc.sz1288;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 */
public class VerticalOrderTraversal {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
    preorder(root, 0, 0, map);

    List<List<Integer>> result = new ArrayList<>();
    for (TreeMap<Integer, List<Integer>> column : map.values()) {
      List<Integer> values = new ArrayList<>();
      column
          .values()
          .forEach(values::addAll);
      result.add(values);
    }
    return result;
  }

  private void preorder(TreeNode node, int order, int depth, TreeMap<Integer, TreeMap<Integer,
      List<Integer>>> map) {
    if (node == null) {
      return;
    }
    TreeMap<Integer, List<Integer>> column = map.getOrDefault(order, new TreeMap<>());
    List<Integer> list = column.getOrDefault(depth, new ArrayList<>());
    list.add(node.val);
    column.put(depth, list);
    map.put(order, column);
    preorder(node.left, order - 1, depth + 1, map);
    preorder(node.right, order + 1, depth + 1, map);
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
