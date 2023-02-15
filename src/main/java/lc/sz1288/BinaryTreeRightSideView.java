package lc.sz1288;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    LinkedList<TreeNode> current = new LinkedList<>();
    current.offer(root);
    while (!current.isEmpty()) {
      TreeNode node = current.peekLast();
      result.add(node.val);

      LinkedList<TreeNode> next = new LinkedList<>();
      for (TreeNode each : current) {
        if (each.left != null) {
          next.add(each.left);
        }
        if (each.right != null) {
          next.add(each.right);
        }
      }
      current = next;
    }
    return result;
  }

  public List<Integer> rightSideViewDfs(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    dfs(root, 0, result);
    return result;
  }

  private void dfs(TreeNode node, int level, List<Integer> result) {
    if (node == null) {
      return;
    }
    if (level == result.size()) {
      result.add(node.val);
    }

    if (node.right != null) {
      dfs(node.right, level + 1, result);
    }
    if (node.left != null) {
      dfs(node.left, level + 1, result);
    }
  }

  public static void main(String[] args) {
    BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
    TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
    System.out.println(solution.rightSideView(root));
    System.out.println(solution.rightSideViewDfs(root));
  }
}
