package com.abnb;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RunLengthDecoder implements Iterator<Integer> {
    private final Iterator<Pair<Integer, Integer>> iterator;
    private int current;
    private int remaining;

    public RunLengthDecoder(List<Pair<Integer, Integer>> decoded) {
        iterator = decoded.iterator();
        current = 0;
        remaining = 0;
        if (iterator.hasNext()) {
            Pair<Integer, Integer> next = iterator.next();
            remaining = next.getLeft();
            current = next.getRight();
        }
    }

    @Override
    public boolean hasNext() {
        return remaining > 0;
    }

    @Override
    public Integer next() {
        int res;
        if (remaining > 0) {
            remaining--;
            res = current;
            if (remaining == 0 && iterator.hasNext()) {
                Pair<Integer, Integer> next = iterator.next();
                remaining = next.getLeft();
                current = next.getRight();
            }
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] ags) {
        RunLengthDecoder sol = new RunLengthDecoder(ImmutableList.of());
        System.out.println(sol.hasNext());
    }
}
