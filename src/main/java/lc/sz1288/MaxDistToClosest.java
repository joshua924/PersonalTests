package lc.sz1288;

/**
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to the closest person.
 */
public class MaxDistToClosest {
  public int maxDistToClosest(int[] seats) {
    int lastPerson = Integer.MIN_VALUE;
    int max = 1;
    for (int i = 0; i < seats.length; i++) {
      if (seats[i] == 1) {
        if (lastPerson < 0) {
          max = Integer.max(max, i);
        } else {
          int space = (i - lastPerson) / 2;
          max = Integer.max(max, space);
        }
        lastPerson = i;
      }
    }
    return Integer.max(max, seats.length - 1 - lastPerson);
  }

  public static void main(String[] args) {
    MaxDistToClosest md = new MaxDistToClosest();
    System.out.println(md.maxDistToClosest(new int[]{1, 0, 0, 0}));
    System.out.println(md.maxDistToClosest(new int[]{0, 0, 1}));
    System.out.println(md.maxDistToClosest(new int[]{1, 0, 0, 0, 1, 0, 1}));
    System.out.println(md.maxDistToClosest(new int[]{0, 1, 1, 1, 1, 1, 0}));
  }
}
