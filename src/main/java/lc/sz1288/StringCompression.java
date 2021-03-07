package lc.sz1288;

import java.util.Arrays;

/**
 * Given an array of characters, compress it in-place.
 * <p>
 * The length after compression must always be smaller than or equal to the original array.
 * <p>
 * Every element of the array should be a character (not int) of length 1.
 * <p>
 * After you are done modifying the input array in-place, return the new length of the array.
 */
public class StringCompression {
    public int compress(char[] chars) {
        int read = 0;
        int write = 0;
        while (read < chars.length) {
            int count = 0;
            chars[write] = chars[read];
            while (read < chars.length && chars[read] == chars[write]) {
                count++;
                read++;
            }
            write++;
            if (count > 1) {
                for (char each : String.valueOf(count).toCharArray()) {
                    chars[write++] = each;
                }
            }
        }
        return write;
    }

    public static void main(String[] args) {
        StringCompression sc = new StringCompression();
        char[] chars = {'a', 'b', 'b', 'c', 'c', 'c', 'c'};
        System.out.println(sc.compress(chars));
        System.out.println(Arrays.toString(chars));
    }
}
