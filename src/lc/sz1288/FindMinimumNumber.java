package lc.sz1288;

import java.util.TreeSet;

public class FindMinimumNumber {
    public int findSecondMinimumValue(TreeNode root) {
        TreeSet<Integer> smallest = new TreeSet<>();
        recurse(root, smallest);
        if (smallest.size() <= 1) {
            return -1;
        }
        smallest.pollFirst();
        return smallest.first();
    }

    private void recurse(TreeNode root, TreeSet<Integer> smallest) {
        smallest.add(root.val);
        if (root.left != null) {
            recurse(root.left, smallest);
            recurse(root.right, smallest);
        }
    }

    public static void main(String[] args) {
        FindMinimumNumber fmn = new FindMinimumNumber();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        System.out.println(fmn.findSecondMinimumValue(root));

        root = new TreeNode(2);
        root.left = new TreeNode(2);
        TreeNode right = new TreeNode(5);
        right.left = new TreeNode(5);
        right.right = new TreeNode(7);
        root.right = right;
        System.out.println(fmn.findSecondMinimumValue(root));
    }
}
