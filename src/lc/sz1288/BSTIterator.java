package lc.sz1288;

import java.util.LinkedList;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class BSTIterator {
    private LinkedList<TreeNode> nodes;

    public BSTIterator(TreeNode root) {
        nodes = new LinkedList<>();
        TreeNode current = root;
        while (current != null) {
            nodes.add(current);
            current = current.left;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !nodes.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode res = nodes.removeLast();
        TreeNode currentNode = res.right;
        while (currentNode != null) {
            nodes.add(currentNode);
            currentNode = currentNode.left;
        }
        return res.val;
    }
}
