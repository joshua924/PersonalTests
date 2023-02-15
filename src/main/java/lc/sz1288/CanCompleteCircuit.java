package lc.sz1288;

/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
 * You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * If there exists a solution, it is guaranteed to be unique
 */
public class CanCompleteCircuit {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int minIndex = 0;
    int cumulativeDiff = 0;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < gas.length; i++) {
      cumulativeDiff += gas[i] - cost[i];
      if (cumulativeDiff < min) {
        min = cumulativeDiff;
        minIndex = i;
      }
    }
    if (cumulativeDiff < 0) {
      return -1;
    }
    return (minIndex + 1) % gas.length;
  }

  public static void main(String[] args) {
    CanCompleteCircuit solution = new CanCompleteCircuit();
    int[] gas = {1, 2, 3, 4, 5};
    int[] cost = {3, 4, 5, 1, 2};
    System.out.println(solution.canCompleteCircuit(gas, cost));
    System.out.println(solution.canCompleteCircuit(new int[]{3, 3, 4}, new int[]{3, 4, 4}));
  }
}
