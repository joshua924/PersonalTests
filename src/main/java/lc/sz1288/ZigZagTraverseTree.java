package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ZigZagTraverseTree {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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
}
