package lc.sz1288;

public class BSTTwoSum {
    public boolean findTarget(TreeNode root, int k) {
        return twoSum(root, root, k);
    }

    private boolean twoSum(TreeNode low, TreeNode high, int k) {
        if (low == null || high == null) {
            return false;
        }
        int sum = low.val + high.val;
        if (sum == k) {
            if (low.val != high.val) {
                return true;
            }
            return twoSum(low.left, high.right, k);
        } else if (sum < k) {
            return twoSum(low.right, high, k) || twoSum(low, high.right, k);
        } else {
            return twoSum(low.left, high, k) || twoSum(low, high.left, k);
        }
    }

    public static void main(String[] args) {
        BSTTwoSum bstTwoSum = new BSTTwoSum();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        System.out.println(bstTwoSum.findTarget(root, 9));
    }
}
