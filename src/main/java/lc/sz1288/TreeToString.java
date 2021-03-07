package lc.sz1288;

public class TreeToString {
    public String tree2str(TreeNode t) {
        StringBuilder str = new StringBuilder();
        if (t == null) {
            return str.toString();
        }
        traverse(t, str);
        return str.toString();
    }

    private void traverse(TreeNode root, StringBuilder str) {
        str.append(root.val);
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left == null) {
            str.append("()(");
            traverse(root.right, str);
            str.append(")");
        } else if (root.right == null) {
            str.append("(");
            traverse(root.left, str);
            str.append(")");
        } else {
            str.append("(");
            traverse(root.left, str);
            str.append(")");
            str.append("(");
            traverse(root.right, str);
            str.append(")");
        }
    }

    public static void main(String[] args) {
        TreeToString tts = new TreeToString();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        left.right = new TreeNode(4);
        root.left = left;
        root.right = new TreeNode(3);
        System.out.println(tts.tree2str(root));
        System.out.println(tts.tree2str(null));
        System.out.println(tts.tree2str(new TreeNode(1)));
    }
}
