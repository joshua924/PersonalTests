package lc.sz1288;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class BuildTree {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> index = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      index.put(inorder[i], i);
    }

    return buildTree(preorder, 0, preorder.length - 1, inorder, 0, preorder.length - 1, index);
  }

  private TreeNode buildTree(int[] preorder, int i1, int j1, int[] inorder, int i2, int j2,
                             Map<Integer, Integer> map) {
    if (i2 > j2) {
      return null;
    }
    if (i2 == j2) {
      return new TreeNode(inorder[i2]);
    }
    int rootVal = preorder[i1];
    int index = map.get(rootVal);
    int leftCount = index - i2;
    TreeNode left = buildTree(preorder, i1 + 1, i1 + leftCount, inorder, i2, index - 1, map);
    TreeNode right = buildTree(preorder, i1 + leftCount + 1, j1, inorder, index + 1, j2, map);
    return new TreeNode(rootVal, left, right);
  }

  public static void main(String[] args) {
    BuildTree solution = new BuildTree();
    int[] preorder = {3, 9, 20, 15, 7};
    int[] inorder = {9, 3, 15, 20, 7};
    System.out.println(solution.buildTree(preorder, inorder));
    System.out.println(solution.buildTree(new int[]{5, 6, 7}, new int[]{7, 6, 5}));
  }
}
