package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

public class ModeInBST {
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Map<Integer, Integer> count = new HashMap<>();
        traverse(root, count);
        int maxValue = count.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        return count.entrySet().stream()
                .filter(entry -> entry.getValue() == maxValue)
                .mapToInt(Map.Entry::getKey).toArray();
    }

    private void traverse(TreeNode root, Map<Integer, Integer> count) {
        if (root == null) {
            return;
        }
        Integer current = count.getOrDefault(root.val, 0);
        count.put(root.val, current + 1);
        traverse(root.left, count);
        traverse(root.right, count);
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
