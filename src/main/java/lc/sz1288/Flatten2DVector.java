package lc.sz1288;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Flatten2DVector implements Iterator<Integer> {
    private Iterator<List<Integer>> listIterator;
    private Iterator<Integer> intIterator;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        listIterator = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return intIterator.next();
    }

    @Override
    public boolean hasNext() {
        while (listIterator.hasNext() && (intIterator == null || !intIterator.hasNext())) {
            intIterator = listIterator.next().iterator();
        }
        return intIterator != null && intIterator.hasNext();
    }

    public static void main(String[] args) {
        List<List<Integer>> vec2d = Arrays.asList(Arrays.asList(), Arrays.asList(1, 2), Arrays.asList(3), Arrays.asList(), Arrays.asList(4, 5, 6));
        Flatten2DVector f2v = new Flatten2DVector(vec2d);
        while (f2v.hasNext()) {
            System.out.println(f2v.next());
        }
    }
}
