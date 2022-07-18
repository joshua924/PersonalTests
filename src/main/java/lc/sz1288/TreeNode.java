package lc.sz1288;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
            "val=" + val +
            '}';
    }
}
