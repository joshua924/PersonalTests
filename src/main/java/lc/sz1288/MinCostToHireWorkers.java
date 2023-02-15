package lc.sz1288;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 * <p>
 * Now we want to hire exactly K workers to form a paid group. When hiring a group of K workers, we must pay them according to the following rules:
 * <p>
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 * <p>
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 */
public class MinCostToHireWorkers {
  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    int len = quality.length;
    Worker[] workers = new Worker[len];
    for (int i = 0; i < len; i++) {
      workers[i] = new Worker(quality[i], wage[i]);
    }
    Arrays.sort(workers);
    double min = Double.MAX_VALUE;
    int qualitySum = 0;
    PriorityQueue<Integer> candidates = new PriorityQueue<>(Comparator.reverseOrder());
    for (Worker worker : workers) {
      candidates.offer(worker.quality);
      qualitySum += worker.quality;
      if (candidates.size() > K) {
        qualitySum -= candidates.poll();
      }
      if (candidates.size() == K) {
        min = Math.min(min, worker.getRatio() * qualitySum);
      }
    }
    return min;
  }

  private static class Worker implements Comparable<Worker> {
    int quality;
    int wage;

    Worker(int quality, int wage) {
      this.quality = quality;
      this.wage = wage;
    }

    double getRatio() {
      return (double) wage / quality;
    }

    @Override
    public int compareTo(Worker o) {
      return Double.compare(getRatio(), o.getRatio());
    }
  }

  public static void main(String[] args) {
    MinCostToHireWorkers mc = new MinCostToHireWorkers();
    System.out.println(mc.mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
    System.out.println(mc.mincostToHireWorkers(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 3));
    System.out.println(mc.mincostToHireWorkers(new int[]{3}, new int[]{5000}, 1));
  }
}
