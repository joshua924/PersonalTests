package lc.sz1288;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSubtree {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> serialized = new ArrayList<>();
        traverse(root, new HashMap<>(), serialized);
        return serialized;
    }

    private String traverse(TreeNode root, Map<String, Integer> sequenceCount, List<TreeNode> serialized) {
        if (root == null) {
            return "#";
        }
        String sequence = Integer.toString(root.val);
        sequence += traverse(root.left, sequenceCount, serialized);
        sequence += traverse(root.right, sequenceCount, serialized);
        int count = sequenceCount.getOrDefault(sequence, 0);
        if (count >= 1) {
            serialized.add(root);
        }
        sequenceCount.put(sequence, count + 1);
        return sequence;
    }
}
