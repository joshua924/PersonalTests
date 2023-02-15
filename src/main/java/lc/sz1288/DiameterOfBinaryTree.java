package lc.sz1288;

import java.util.concurrent.atomic.AtomicInteger;

public class DiameterOfBinaryTree {
  public int diameterOfBinaryTree(TreeNode root) {
    AtomicInteger result = new AtomicInteger(0);
    int rootLength = maxLength(root, result);
    return Math.max(rootLength, result.get());
  }

  private int maxLength(TreeNode node, AtomicInteger result) {
    if (node == null) {
      return -1;
    }
    int leftLength = maxLength(node.left, result);
    int rightLength = maxLength(node.right, result);
    result.set(Math.max(result.get(), leftLength + rightLength + 2));
    return Math.max(leftLength, rightLength) + 1;
  }

  public static void main(String[] args) {
    DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
    TreeNode left = new TreeNode(2, new TreeNode(4, new TreeNode(7), null), new TreeNode(5, null, new TreeNode(8)));
    TreeNode root = new TreeNode(1, left, new TreeNode(3));
    System.out.println(solution.diameterOfBinaryTree(root));
  }
}
