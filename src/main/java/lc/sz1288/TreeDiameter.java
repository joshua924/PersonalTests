package lc.sz1288;

public class TreeDiameter {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = new int[1];
        maxPath(root, max);
        return max[0];
    }

    private int maxPath(TreeNode root, int[] max) {
        if (root == null) {
            return -1;
        }
        int left = maxPath(root.left, max);
        int right = maxPath(root.right, max);
        max[0] = Math.max(max[0], left + right + 2);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeDiameter td = new TreeDiameter();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        root.right = new TreeNode(3);
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);
        root.left = left;
        System.out.println(td.diameterOfBinaryTree(root));
        System.out.println(td.diameterOfBinaryTree(left));
    }
}
