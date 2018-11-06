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
    private LinkedList<TreeNode> nodes = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        TreeNode current = root;
        addAllLeftChildren(current);
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
        TreeNode res = nodes.pop();
        addAllLeftChildren(res.right);
        return res.val;
    }

    private void addAllLeftChildren(TreeNode current) {
        while (current != null) {
            nodes.push(current);
            current = current.left;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        BSTIterator bsti = new BSTIterator(root);
        while (bsti.hasNext()) {
            System.out.println(bsti.next());
        }
    }
}
