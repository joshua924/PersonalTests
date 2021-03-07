package lc.sz1288;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DistributeCandy {
    public int distributeCandies(int[] candies) {
        return Math.min(IntStream.of(candies).boxed().collect(Collectors.toSet()).size(), candies.length / 2);
    }
}
