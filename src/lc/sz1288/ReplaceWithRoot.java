package lc.sz1288;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReplaceWithRoot {
    public String replaceWords(List<String> dict, String sentence) {
        int maxLen = 0;
        Set<String> roots = new HashSet<>();
        for (String s : dict) {
            maxLen = Math.max(maxLen, s.length());
            roots.add(s);
        }
        String[] tokens = sentence.split(" ");
        for (int j = 0; j < tokens.length; j++) {
            String token = tokens[j];
            for (int i = 0; i <= Math.min(token.length(), maxLen); i++) {
                String substring = token.substring(0, i);
                if (roots.contains(substring)) {
                    tokens[j] = substring;
                    break;
                }
            }
        }
        return String.join(" ", tokens);
    }

    public static void main(String[] args) {
        ReplaceWithRoot rwr = new ReplaceWithRoot();
        List<String> dict = Arrays.asList("cat", "bat", "rat");
        System.out.println(rwr.replaceWords(dict, "the cattle was rattled by the battery"));
        System.out.println(rwr.replaceWords(dict, "the cat was rattled by the battery"));
    }
}
