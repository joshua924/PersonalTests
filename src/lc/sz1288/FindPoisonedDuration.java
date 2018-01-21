package lc.sz1288;

public class FindPoisonedDuration {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int index = 0;
        int sum = 0;
        while (index < timeSeries.length) {
            int start = timeSeries[index];
            int currentPoisonEnd = timeSeries[index];
            while (index < timeSeries.length && timeSeries[index] <= currentPoisonEnd) {
                currentPoisonEnd = timeSeries[index] + duration;
                index++;
            }
            sum += currentPoisonEnd - start;
        }
        return sum;
    }

    public static void main(String[] args) {
        FindPoisonedDuration fp = new FindPoisonedDuration();
        int[] timeSeries = {1, 2};
        System.out.println(fp.findPoisonedDuration(timeSeries, 2));
    }
}
