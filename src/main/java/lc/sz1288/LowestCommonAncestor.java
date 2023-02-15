package lc.sz1288;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the
 * lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 */
public class LowestCommonAncestor {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
    TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);
    if (leftAncestor != null && rightAncestor != null) {
      return root;
    } else if (rightAncestor != null) {
      return rightAncestor;
    } else {
      return leftAncestor;
    }
  }

  public static void main(String[] args) {
    TreeNode left = new TreeNode(1);
    TreeNode right = new TreeNode(5);
    TreeNode root = new TreeNode(3);
    root.left = left;
    root.right = right;
    LowestCommonAncestor solution = new LowestCommonAncestor();
    System.out.println(solution.lowestCommonAncestor(root, left, right));
  }
}
