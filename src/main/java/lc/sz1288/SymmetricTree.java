package lc.sz1288;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return isMirrored(root.left, root.right);
    }

    private boolean isMirrored(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        return n1.val == n2.val && isMirrored(n1.left, n2.right) && isMirrored(n1.right, n2.left);
    }

    public static void main(String[] args) {
        SymmetricTree solution = new SymmetricTree();
        System.out.println(solution.isSymmetric(new TreeNode(2, new TreeNode(3), new TreeNode(3))));
    }
}
