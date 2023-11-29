package com.abnb;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

public class RunLengthEncoding {
    public List<Pair<Integer, Integer>> encode(List<Integer> input) {
        if (CollectionUtils.isEmpty(input)) {
            return ImmutableList.of();
        }
        int head = input.get(0);
        int length = 0;
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int each : input) {
            if (each == head) {
                length++;
            } else {
                result.add(Pair.of(length, head));
                head = each;
                length = 1;
            }
        }
        result.add(Pair.of(length, head));
        return result;
    }

    public List<Triple<Integer, Integer, Integer>> encodeVariation(List<Integer> input) {
        if (CollectionUtils.isEmpty(input)) {
            return ImmutableList.of();
        }
        int head = input.get(0);
        int length = 1;
        int diff = 0;
        boolean start = true;
        List<Triple<Integer, Integer, Integer>> result = new ArrayList<>();
        for (int i = 1; i < input.size(); i++) {
            int each = input.get(i);
            if (start) {
                diff = each - head;
                length++;
                start = false;
                continue;
            }
            if (each - input.get(i - 1) == diff) {
                length++;
            } else {
                result.add(Triple.of(length, head, diff));
                head = each;
                start = true;
                length = 1;
            }
        }
        result.add(Triple.of(length, head, diff));
        return result;
    }

    public static void main(String[] args) {
        RunLengthEncoding sol = new RunLengthEncoding();
        List<Pair<Integer, Integer>> encoded = sol.encode(ImmutableList.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5));
        RunLengthDecoder decoder = new RunLengthDecoder(encoded);
        while (decoder.hasNext()) {
            System.out.println(decoder.next());
        }
        System.out.println("------------");

        List<Triple<Integer, Integer, Integer>> encoded1 = sol.encodeVariation(ImmutableList.of(10, 10, 10, 10, 10));
        RunLengthVariationDecoder d = new RunLengthVariationDecoder(encoded1);
        while (d.hasNext()) {
            System.out.println(d.next());
        }
        System.out.println("------------");

        List<Triple<Integer, Integer, Integer>> encoded2 = sol.encodeVariation(ImmutableList.of(5, 4, 3, 2, 1));
        d = new RunLengthVariationDecoder(encoded2);
        while (d.hasNext()) {
            System.out.println(d.next());
        }
        System.out.println("------------");

        List<Triple<Integer, Integer, Integer>> encoded3 = sol.encodeVariation(ImmutableList.of(10, 11, 12, 22, 23, 24));
        d = new RunLengthVariationDecoder(encoded3);
        while (d.hasNext()) {
            System.out.println(d.next());
        }
        System.out.println("------------");
    }
}
