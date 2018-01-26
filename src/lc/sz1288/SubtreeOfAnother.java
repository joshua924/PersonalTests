package lc.sz1288;

public class SubtreeOfAnother {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        StringBuilder s1 = new StringBuilder();
        traverse(s, s1);
        System.out.println(s1.toString());
        StringBuilder s2 = new StringBuilder();
        traverse(t, s2);
        System.out.println(s2.toString());
        return s1.toString().contains(s2.toString());
    }

    private void traverse(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        traverse(root.left, sb);
        sb.append(root.val).append(',');
        traverse(root.right, sb);
        sb.append('|');
    }
}
