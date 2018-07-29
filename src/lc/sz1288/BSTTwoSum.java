package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

public class BSTTwoSum {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> traverse = new ArrayList<>();
        inOrderTraverse(root, traverse);
        return twoSum(traverse, k);
    }

    private void inOrderTraverse(TreeNode root, List<Integer> traverse) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left, traverse);
        traverse.add(root.val);
        inOrderTraverse(root.right, traverse);
    }

    private boolean twoSum(List<Integer> traverse, int k) {
        int head = 0;
        int tail = traverse.size() - 1;
        while (head < tail) {
            if (traverse.get(head) + traverse.get(tail) < k) {
                head++;
            } else if (traverse.get(head) + traverse.get(tail) > k) {
                tail--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BSTTwoSum bstTwoSum = new BSTTwoSum();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        System.out.println(bstTwoSum.findTarget(root, 9));
    }
}
