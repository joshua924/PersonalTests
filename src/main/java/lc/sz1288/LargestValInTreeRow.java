package lc.sz1288;

import java.util.ArrayList;
import java.util.List;

public class LargestValInTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> maxVal = new ArrayList<>();
        largestValues(root, maxVal, 0);
        return maxVal;
    }

    private void largestValues(TreeNode root, ArrayList<Integer> maxVal, int level) {
        if (root == null) {
            return;
        }
        if (level >= maxVal.size()) {
            maxVal.add(root.val);
        } else {
            maxVal.set(level, Math.max(root.val, maxVal.get(level)));
        }
        largestValues(root.left, maxVal, level + 1);
        largestValues(root.right, maxVal, level + 1);
    }
}
