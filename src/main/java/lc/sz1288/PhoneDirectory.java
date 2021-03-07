package lc.sz1288;

import java.util.BitSet;

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
    BitSet bitSet;
    int max;
    int smallestClearIndex;

    public PhoneDirectory(int maxNumbers) {
        bitSet = new BitSet(maxNumbers);
        max = maxNumbers;
        smallestClearIndex = 0;
    }

    public int get() {
        if (smallestClearIndex == max) {
            return -1;
        }
        int num = smallestClearIndex;
        bitSet.set(smallestClearIndex);
        smallestClearIndex = bitSet.nextClearBit(smallestClearIndex);
        return num;
    }

    public boolean check(int number) {
        return !bitSet.get(number);
    }

    public void release(int number) {
        bitSet.clear(number);
        if (number < smallestClearIndex) {
            smallestClearIndex = number;
        }
    }

    public static void main(String[] args) {
        PhoneDirectory pd = new PhoneDirectory(3);
        System.out.println(pd.get());
        System.out.println(pd.get());
        System.out.println(pd.check(2));
        System.out.println(pd.get());
        System.out.println(pd.check(2));
        pd.release(2);
        System.out.println(pd.check(2));
    }
}
