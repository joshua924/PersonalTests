package lc.sz1288;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
public class TreeSerDe {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        return deserialize(new LinkedList<>(Arrays.asList(tokens)));
    }

    private TreeNode deserialize(Queue<String> tokens) {
        String poll = tokens.remove();
        if (poll.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(poll));
        root.left = deserialize(tokens);
        root.right = deserialize(tokens);
        return root;
    }

    public static void main(String[] args) {
        TreeSerDe tsd = new TreeSerDe();
        TreeNode root = tsd.deserialize("1,2,6,#,#,9,7,#,#,#,3,#,5,#,12,#,#");
        System.out.println(root);
        System.out.println(tsd.serialize(root));
        System.out.println(tsd.serialize(null));
    }
}
