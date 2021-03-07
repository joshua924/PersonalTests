package lc.sz1288;

public class TreeTilt {
    public int findTilt(TreeNode root) {
        int[] res = new int[1];
        computeTilt(root, res);
        return res[0];
    }

    private int computeTilt(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int leftSum = computeTilt(root.left, res);
        int rightSum = computeTilt(root.right, res);
        res[0] += Math.abs(leftSum - rightSum);
        return leftSum + root.val + rightSum;
    }

    public static void main(String[] args) {
        TreeTilt tt = new TreeTilt();
        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode root = new TreeNode(1, left, new TreeNode(3));
        System.out.println(tt.findTilt(root));
    }
}
