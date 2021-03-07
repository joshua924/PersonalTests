package lc.sz1288;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrintBinaryTreeIn2DArray {
    public List<List<String>> printTree(TreeNode root) {
        int length = length(root);
        List<String[]> matrix = new ArrayList<>();
        printTree(root, matrix, 0, 0, length - 1, length);
        return matrix.stream().map(Arrays::asList).collect(Collectors.toList());
    }

    private void printTree(TreeNode root, List<String[]> matrix, int level, int lower, int upper, int length) {
        if (root == null) {
            return;
        }
        if (matrix.size() < level + 1) {
            String[] strings = new String[length];
            Arrays.fill(strings, "");
            matrix.add(strings);
        }
        int index = (lower + upper) / 2;
        matrix.get(level)[index] = Integer.toString(root.val);
        printTree(root.left, matrix, level + 1, lower, index, length);
        printTree(root.right, matrix, level + 1, index + 1, upper, length);
    }

    private int length(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(length(root.left), length(root.right)) * 2;
    }

    public static void main(String[] args) {
        PrintBinaryTreeIn2DArray pbt = new PrintBinaryTreeIn2DArray();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        left.right = new TreeNode(4);
        root.left = left;
        root.right = new TreeNode(3);
        System.out.println(pbt.printTree(root));
    }
}
