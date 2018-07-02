package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<List<TreeNode>> nodes = new ArrayList<>();
        if (root == null) {
            return Collections.emptyList();
        }
        List<TreeNode> current = Collections.singletonList(root);
        while (!current.isEmpty()) {
            nodes.add(current);
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : current) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            current = nextLevel;
        }
        return nodes.stream()
                .map(nodeList -> nodeList.stream().mapToInt(node -> node.val).average().getAsDouble())
                .collect(Collectors.toList());
    }
}
