package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintTree {
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] matrix = new String[height][(1 << height) - 1];
        for (String[] arr : matrix) {
            Arrays.fill(arr, "");
        }
        List<List<String>> res = new ArrayList<>();
        fill(matrix, root, 0, 0, matrix[0].length);
        for (String[] row : matrix) {
            res.add(Arrays.asList(row));
        }
        return res;
    }

    private void fill(String[][] matrix, TreeNode root, int level, int l, int r) {
        if (root == null) {
            return;
        }
        matrix[level][(l + r) / 2] = "" + root.val;
        fill(matrix, root.left, level + 1, l, (l + r) / 2);
        fill(matrix, root.right, level + 1, (l + r + 1) / 2, r);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
