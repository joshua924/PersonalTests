package lc.sz1288;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterNumberII {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] nexts = new int[n];
        Arrays.fill(nexts, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                nexts[stack.pop()] = num;
            if (i < n) {
                stack.push(i);
            }
        }
        return nexts;
    }

    public static void main(String[] args) {
        NextGreaterNumberII ng = new NextGreaterNumberII();
        int[] nums = {1, 2, 1, 4, 5, 9, 3};
        System.out.println(Arrays.toString(ng.nextGreaterElements(nums)));
    }
}
