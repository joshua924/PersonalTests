package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ZigZagTraverseTree {
    public List<List<Integer>> zigzagLevelOrderIterative(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> level = new ArrayList<>();
        level.add(root);
        boolean reverse = false;
        while (!level.isEmpty()) {
            List<Integer> list = level.stream().map(node -> node.val).collect(Collectors.toList());
            if (reverse) {
                Collections.reverse(list);
            }
            res.add(list);
            reverse = !reverse;
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode each : level) {
                if (each.left != null) {
                    next.add(each.left);
                }
                if (each.right != null) {
                    next.add(each.right);
                }
            }
            level = new ArrayList<>(next);
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<LinkedList<Integer>> result = new ArrayList<>();

        recurseZigzag(root, 0, false, result);

        return new ArrayList<>(result);
    }

    private void recurseZigzag(TreeNode node, int level, boolean reverse, List<LinkedList<Integer>> result) {
        if (node == null) {
            return;
        }
        if (result.size() <= level) {
            result.add(new LinkedList<>());
        }
        if (reverse) {
            result.get(level).addFirst(node.val);
        } else {
            result.get(level).addLast(node.val);
        }

        recurseZigzag(node.left, level + 1, !reverse, result);
        recurseZigzag(node.right, level + 1, !reverse, result);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        ZigZagTraverseTree solution = new ZigZagTraverseTree();
        System.out.println(solution.zigzagLevelOrderIterative(root));
        System.out.println(solution.zigzagLevelOrder(root));
    }
}
