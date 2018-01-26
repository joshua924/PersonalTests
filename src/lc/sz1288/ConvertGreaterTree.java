package lc.sz1288;

public class ConvertGreaterTree {
    public TreeNode convertBST(TreeNode root) {
        int[] sum = new int[1];
        traverse(root, sum);
        return root;
    }

    private void traverse(TreeNode root, int[] sum) {
        if (root == null) {
            return;
        }
        traverse(root.right, sum);
        root.val += sum[0];
        sum[0] = root.val;
        traverse(root.left, sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        TreeNode newRoot = new ConvertGreaterTree().convertBST(root);
        System.out.println(newRoot.val + "," + newRoot.left.val + "," + newRoot.right.val);
    }
}
