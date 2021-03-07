package lc.sz1288;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindReplaceString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if (indexes.length == 0) {
            return S;
        }
        List<IndexString> replacements = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) {
            replacements.add(new IndexString(indexes[i], sources[i], targets[i]));
        }
        Collections.sort(replacements);
        StringBuilder res = new StringBuilder(S.substring(0, replacements.get(0).index));
        for (int i = 0; i < replacements.size(); i++) {
            IndexString indexString = replacements.get(i);
            String substring = (i == replacements.size() - 1) ? S.substring(indexString.index) : S.substring(indexString.index, replacements.get(i + 1).index);
            res.append(indexString.replace(substring));
        }
        return res.toString();
    }

    private static class IndexString implements Comparable<IndexString> {
        int index;
        String source;
        String target;

        IndexString(int index, String source, String target) {
            this.index = index;
            this.source = source;
            this.target = target;
        }

        String replace(String S) {
            if (S.startsWith(source)) {
                return S.replaceFirst(source, target);
            } else {
                return S;
            }
        }

        @Override
        public int compareTo(IndexString o) {
            return index - o.index;
        }
    }

    public static void main(String[] args) {
        FindReplaceString frs = new FindReplaceString();
        // S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
        System.out.println(frs.findReplaceString("abcd", new int[]{1, 2}, new String[]{"b", "cd"}, new String[]{"eee", "ffff"}));
        // S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
        System.out.println(frs.findReplaceString("abcd", new int[]{0, 2}, new String[]{"ab", "ec"}, new String[]{"eee", "ffff"}));
    }
}
