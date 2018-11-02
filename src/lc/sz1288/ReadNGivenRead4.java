package lc.sz1288;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * <p>
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * <p>
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * <p>
 * Example 1:
 * <p>
 * Input: buf = "abc", n = 4
 * Output: "abc"
 * Explanation: The actual number of characters read is 3, which is "abc".
 * Example 2:
 * <p>
 * Input: buf = "abcde", n = 5
 * Output: "abcde"
 * Note:
 * The read function will be called multiple times.
 */
public class ReadNGivenRead4 extends Reader4 {
    private char[] buffer = new char[4];
    private int pointer = 0;
    private int available = 0;

    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (pointer == 0) {
                available = read4(buffer);
                if (available == 0) {
                    break;
                }
            }
            while (ptr < n && pointer < available) {
                buf[ptr++] = buffer[pointer++];
            }
            if (pointer == available) {
                pointer = 0;
            }
        }
        return ptr;
    }
}
