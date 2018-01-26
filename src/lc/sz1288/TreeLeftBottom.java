package lc.sz1288;

public class TreeLeftBottom {
    public int findBottomLeftValue(TreeNode root) {
        int[] val = new int[2];
        traverse(root, 0, val);
        return val[1];
    }

    private void traverse(TreeNode root, int level, int[] val) {
        if (root == null) {
            return;
        }
        if (level >= val[0]) {
            val[0] = level;
            val[1] = root.val;
        }
        traverse(root.right, level + 1, val);
        traverse(root.left, level + 1, val);
    }
}
