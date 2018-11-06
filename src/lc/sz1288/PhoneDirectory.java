package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Design a Phone Directory which supports the following operations:
 * <p>
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 * Example:
 * <p>
 * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 * <p>
 * // It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 * <p>
 * // Assume it returns 1.
 * directory.get();
 * <p>
 * // The number 2 is available, so return true.
 * directory.check(2);
 * <p>
 * // It returns 2, the only number that is left.
 * directory.get();
 * <p>
 * // The number 2 is no longer available, so return false.
 * directory.check(2);
 * <p>
 * // Release number 2 back to the pool.
 * directory.release(2);
 * <p>
 * // Number 2 is available again, return true.
 * directory.check(2);
 */
public class PhoneDirectory {
    Set<Integer> used = new HashSet<>();
    Queue<Integer> available = new LinkedList<>();
    int max;

    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        for (int i = 0; i < maxNumbers; i++) {
            available.offer(i);
        }
    }

    public int get() {
        Integer ret = available.poll();
        if (ret == null) {
            return -1;
        }
        used.add(ret);
        return ret;
    }

    public boolean check(int number) {
        return !used.contains(number);
    }

    public void release(int number) {
        if (used.remove(number)) {
            available.offer(number);
        }
    }

    public static void main(String[] args) {
        PhoneDirectory pd = new PhoneDirectory(0);
        System.out.println(pd.get());
    }
}
