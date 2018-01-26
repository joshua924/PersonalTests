package lc.sz1288;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<LinkedList<Integer>> sequences = new LinkedList<>();
        for (int num : nums) {
            List<LinkedList<Integer>> newSequences = new LinkedList<>();
            for (LinkedList<Integer> sequence : sequences) {
                if (num >= sequence.getLast()) {
                    LinkedList<Integer> newSequence = new LinkedList<>(sequence);
                    newSequence.add(num);
                    newSequences.add(newSequence);
                }
            }
            sequences.addAll(newSequences);
            sequences.add(new LinkedList<>(Collections.singletonList(num)));
        }
        return sequences.stream().filter(list -> list.size() >= 2).distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        IncreasingSubsequences is = new IncreasingSubsequences();
        int[] nums = {4, 6, 7, 7};
        System.out.println(is.findSubsequences(nums));
    }
}
