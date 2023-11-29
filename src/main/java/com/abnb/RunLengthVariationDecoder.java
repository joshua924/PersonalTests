package com.abnb;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RunLengthVariationDecoder implements Iterator<Integer> {
    private final Iterator<Triple<Integer, Integer, Integer>> iterator;
    private int current;
    private int remaining;
    private int diff;

    public RunLengthVariationDecoder(List<Triple<Integer, Integer, Integer>> decoded) {
        iterator = decoded.iterator();
        current = 0;
        remaining = 0;
        diff = 0;
        if (iterator.hasNext()) {
            Triple<Integer, Integer, Integer> next = iterator.next();
            remaining = next.getLeft();
            current = next.getMiddle();
            diff = next.getRight();
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
            current += diff;
            if (remaining == 0 && iterator.hasNext()) {
                Triple<Integer, Integer, Integer> next = iterator.next();
                remaining = next.getLeft();
                current = next.getMiddle();
                diff = next.getRight();
            }
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] ags) {
        RunLengthVariationDecoder sol = new RunLengthVariationDecoder(ImmutableList.of());
        System.out.println(sol.hasNext());
    }
}
