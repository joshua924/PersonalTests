package lc.sz1288;

public class AddRowToTree {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        addOneRow(root, v, d, 1);
        return root;
    }

    private void addOneRow(TreeNode root, int v, int d, int currentDepth) {
        if (root == null) {
            return;
        }
        if (currentDepth != d - 1) {
            addOneRow(root.left, v, d, currentDepth + 1);
            addOneRow(root.right, v, d, currentDepth + 1);
        } else {
            if (root.left != null) {
                TreeNode node = new TreeNode(v);
                node.left = root.left;
                root.left = node;
            } else {
                root.left = new TreeNode(v);
            }
            if (root.right != null) {
                TreeNode node = new TreeNode(v);
                node.right = root.right;
                root.right = node;
            } else {
                root.right = new TreeNode(v);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        left.left = new TreeNode(3);
        left.right = new TreeNode(1);
        root.left = left;
        AddRowToTree ar = new AddRowToTree();
        System.out.println(ar.addOneRow(root, 1, 3));
    }
}
