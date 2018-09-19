package lc.sz1288;

/**
 * You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps.
 * Conversely, if it's negative (-n), move backward n steps.
 * Assume the first element of the array is forward next to the last element,
 * and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts
 * and ends at a particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.
 * <p>
 * Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.
 * <p>
 * Example 2: Given the array [-1, 2], there is no loop.
 * <p>
 * Note: The given array is guaranteed to contain no element "0".
 */
public class CircularArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        for (int pos = 0; pos < nums.length; pos++) {
            if (nums[pos] == 0) {
                continue;
            }
            int steps = 0;
            int current = pos;
            int direction = nums[pos];
            while (Math.abs(nums[current]) != nums.length && nums[current] * direction >= 0) {
                if (nums[current] == 0) {
                    return steps > 1;
                }
                int move = nums[current];
                nums[current] = 0;
                current += move;
                steps++;
                if (current >= nums.length) {
                    current -= nums.length;
                }
                if (current < 0) {
                    current += nums.length;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CircularArrayLoop cal = new CircularArrayLoop();
//        System.out.println(cal.circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
        System.out.println(cal.circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
    }
}
