package lc.sz1288;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SubtreeSum {
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Map<Integer, Integer> sumCount = new HashMap<>();
        traverse(root, sumCount);
        int max = Collections.max(sumCount.values());
        return sumCount.entrySet().stream().filter(entry -> entry.getValue() == max).mapToInt(Map.Entry::getKey).toArray();
    }

    private int traverse(TreeNode root, Map<Integer, Integer> sumCount) {
        if (root == null) {
            return 0;
        }
        int leftSum = traverse(root.left, sumCount);
        int rightSum = traverse(root.right, sumCount);
        int sum = leftSum + rightSum + root.val;
        sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
        return sum;
    }

}
