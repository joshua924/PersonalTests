package lc.sz1288;

public class DirectionInBinaryTree {
  public String getDirections(TreeNode root, int startValue, int destValue) {
    TreeNode lca = findLowestAncestor(root, startValue, destValue);
    String[] paths = new String[2];
    dfs(lca, "", startValue, destValue, paths);

    return paths[0].replaceAll(".", "U") + paths[1];
  }

  private TreeNode findLowestAncestor(TreeNode root, int p, int q) {
    if (root == null || root.val == p || root.val == q) {
      return root;
    }
    TreeNode leftAncestor = findLowestAncestor(root.left, p, q);
    TreeNode rightAncestor = findLowestAncestor(root.right, p, q);
    if (leftAncestor != null && rightAncestor != null) {
      return root;
    } else if (rightAncestor != null) {
      return rightAncestor;
    } else {
      return leftAncestor;
    }
  }

  private void dfs(TreeNode node, String current, int startValue, int destValue, String[] paths) {
    if (node == null || paths[0] != null && paths[1] != null) {
      return;
    }
    if (node.val == startValue) {
      paths[0] = current;
    }
    if (node.val == destValue) {
      paths[1] = current;
    }
    dfs(node.left, current + "L", startValue, destValue, paths);
    dfs(node.right, current + "R", startValue, destValue, paths);
  }

  public static void main(String[] args) {
    DirectionInBinaryTree solution = new DirectionInBinaryTree();
    TreeNode root = new TreeNode(2, new TreeNode(1), null);
    System.out.println(solution.getDirections(root, 2, 1));
  }
}
