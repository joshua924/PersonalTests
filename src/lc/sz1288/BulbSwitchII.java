package lc.sz1288;

/**
 * There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons,
 * you need to return how many different kinds of status of the n lights could be.
 * <p>
 * Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
 * <p>
 * Flip all the lights.
 * Flip lights with even numbers.
 * Flip lights with odd numbers.
 * Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 */
public class BulbSwitchII {
    public int flipLights(int n, int m) {
        if (m == 0 || n == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (m == 1) {
            return Math.min(n + 1, 4);
        }
        if (n == 2) {
            return 4;
        }
        if (m == 2) {
            return 7;
        }
        return 8;
    }
}
