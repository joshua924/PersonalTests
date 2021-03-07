package lc.sz1288;

/**
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to closest person.
 */
public class MaxDistToClosest {
    public int maxDistToClosest(int[] seats) {
        int lastPerson = -1;
        int i = 0;
        int max = 0;
        while (i < seats.length) {
            if (seats[i] == 1) {
                lastPerson = i;
                i++;
            } else {
                while (i < seats.length && seats[i] == 0) {
                    i++;
                }
                if (lastPerson < 0) {
                    max = Math.max(max, i);
                } else {
                    max = Math.max(max, (i - lastPerson) / 2);
                }
            }
        }
        return Math.max(max, seats.length - lastPerson - 1);
    }

    public static void main(String[] args) {
        MaxDistToClosest md = new MaxDistToClosest();
        System.out.println(md.maxDistToClosest(new int[]{1, 0, 0, 0}));
        System.out.println(md.maxDistToClosest(new int[]{0, 0, 1}));
        System.out.println(md.maxDistToClosest(new int[]{0, 0, 0, 0, 1, 0, 1}));
        System.out.println(md.maxDistToClosest(new int[]{0, 1, 1, 1, 1, 1, 0}));
    }
}
