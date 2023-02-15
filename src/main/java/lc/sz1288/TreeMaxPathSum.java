package lc.sz1288;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path
 */
public class TreeMaxPathSum {
  public int maxPathSum(TreeNode root) {
    AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
    maxPathEndingWith(root, max);
    return max.get();
  }

  private int maxPathEndingWith(TreeNode node, AtomicInteger max) {
    if (node == null) {
      return 0;
    }
    int leftMax = maxPathEndingWith(node.left, max);
    int rightMax = maxPathEndingWith(node.right, max);
    int throughNode = node.val + leftMax + rightMax;
    int endWithNodeMax = Math.max(node.val, Math.max(node.val + rightMax, node.val + leftMax));
    max.set(Math.max(max.get(), Math.max(throughNode, endWithNodeMax)));
    return endWithNodeMax;
  }

  public static void main(String[] args) {
    TreeMaxPathSum solution = new TreeMaxPathSum();
    TreeNode root = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
    System.out.println(solution.maxPathSum(root));
    System.out.println(solution.maxPathSum(new TreeNode(8)));
  }
}
