package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

public class MinimumDifferenceInBST {
    public int getMinimumDifference(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        traverse(root, nodes);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nodes.size() - 1; i++) {
            minDiff = Math.min(minDiff, nodes.get(i + 1) - nodes.get(i));
        }
        return minDiff;
    }

    private void traverse(TreeNode root, List<Integer> nodes) {
        if (root == null) {
            return;
        }
        traverse(root.left, nodes);
        nodes.add(root.val);
        traverse(root.right, nodes);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
