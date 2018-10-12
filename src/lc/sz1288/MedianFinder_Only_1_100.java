package lc.sz1288;

/**
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFinder_Only_1_100 implements MedianFinder {
    private int[] count;
    private int total;

    /**
     * initialize your data structure here.
     */
    public MedianFinder_Only_1_100() {
        count = new int[100];
        total = 0;
    }

    @Override
    public void addNum(int num) {
        count[num - 1]++;
        total++;
    }

    @Override
    public double findMedian() {
        int index = 0;
        int cell = 0;
        while (index <= total / 2) {
            index += count[cell++];
        }
        if (total % 2 != 0) {
            return cell;
        }
        int m1 = cell;
        cell--;
        while (index >= total / 2) {
            index -= count[cell--];
        }
        return m1 / 2.0 + (cell + 2) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder_Only_1_100 mf = new MedianFinder_Only_1_100();
        mf.addNum(1);
        mf.addNum(3);
        mf.addNum(5);
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }
}
