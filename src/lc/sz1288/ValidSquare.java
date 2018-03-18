package lc.sz1288;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Double> lengths = Stream.of(edgeLength(p1, p2), edgeLength(p1, p3), edgeLength(p1, p4), edgeLength(p2, p3), edgeLength(p2, p4), edgeLength(p3, p4)).collect(Collectors.toSet());
        return !lengths.contains(0) && lengths.size() == 2;
    }

    private double edgeLength(int[] p1, int[] p2) {
        return Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2);
    }
}
