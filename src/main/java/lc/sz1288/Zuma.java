package lc.sz1288;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Zuma {
    public int findMinStep(String board, String hand) {
        List<Sequence> sequences = new LinkedList<>();
        char[] chars = board.toCharArray();
        char cur = chars[0];
        int len = 1;
        int index = 1;
        while (index < chars.length) {
            if (chars[index] == cur) {
                len++;
                index++;
            } else {
                sequences.add(new Sequence(cur, len));
                len = 1;
                cur = chars[index++];
            }
        }
        sequences.add(new Sequence(cur, len));

        int min = Integer.MAX_VALUE;
        for (String s : permute(hand)) {
            int minStep = findMinStepFix(copy(sequences), s);
            min = Math.min(min, minStep);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int findMinStepFix(List<Sequence> sequences, String hand) {
        int index = 0;
        while (!sequences.isEmpty() && index < hand.length()) {
            addBall(sequences, hand.charAt(index++));
        }
        return sequences.isEmpty() ? index : Integer.MAX_VALUE;
    }

    private Set<String> permute(String hand) {
        Set<String> res = new HashSet<>();
        permute(res, "", hand);
        return res;
    }

    private static void permute(Set<String> res, String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            res.add(prefix);
            return;
        }
        for (int i = 0; i < n; i++) {
            permute(res, prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }

    private List<Sequence> copy(List<Sequence> sequences) {
        return sequences.stream().map(sequence -> new Sequence(sequence.color, sequence.length)).collect(Collectors.toList());
    }

    public static void addBall(List<Sequence> sequences, char ball) {
        if (sequences.remove(new Sequence(ball, 2))) {
            boolean sameColor = false;
            int index = 0;
            while (index < sequences.size() - 1) {
                if (sequences.get(index).color == sequences.get(index + 1).color) {
                    sameColor = true;
                    break;
                }
                index++;
            }
            if (sameColor) {
                int totalLength = sequences.get(index).length + sequences.get(index + 1).length;
                sequences.remove(index + 1);
                if (totalLength >= 3) {
                    sequences.remove(index);
                } else {
                    sequences.get(index).length = totalLength;
                }
            }
        } else {
            int index = sequences.indexOf(new Sequence(ball, 1));
            if (index >= 0) {
                sequences.set(index, new Sequence(ball, 2));
            } else {
                sequences.add(new Sequence(ball, 1));
            }
        }
    }

    public static class Sequence {
        char color;
        int length;

        public Sequence(char color, int length) {
            this.color = color;
            this.length = length;
        }

        @Override
        public boolean equals(Object o) {
            Sequence sequence = (Sequence) o;
            return color == sequence.color &&
                    length == sequence.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(color, length);
        }
    }

    public static void main(String[] args) {
        Zuma zuma = new Zuma();
        System.out.println(zuma.findMinStep("WWGWGW", "WWGBR"));
    }
}
