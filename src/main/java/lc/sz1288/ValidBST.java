package lc.sz1288;

public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValueBetween(root, (long) Integer.MIN_VALUE - 1, (long) Integer.MAX_VALUE + 1);
    }

    private boolean isValueBetween(TreeNode root, long minValue, long maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val <= minValue || root.val >= maxValue) {
            return false;
        }
        return isValueBetween(root.left, minValue, root.val) && isValueBetween(root.right, root.val, maxValue);
    }

    public static void main(String[] args) {
        ValidBST solution = new ValidBST();
        System.out.println(solution.isValidBST(new TreeNode(2, new TreeNode(1), new TreeNode(3))));
    }
}
