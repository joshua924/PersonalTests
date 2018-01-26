package lc.sz1288;

public class ReverseStringII {
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int index = 0;
        while (index <= sb.length()) {
            int newIndex = Math.min(sb.length(), index + k);
            String newString = new StringBuilder(sb.substring(index, newIndex)).reverse().toString();
            sb.replace(index, newIndex, newString);
            index = newIndex;
            index += k;
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            split[i] = new StringBuilder(split[i]).reverse().toString();
        }
        return String.join(" ", split);
    }

    public static void main(String[] args) {
        ReverseStringII rs = new ReverseStringII();
        System.out.println(rs.reverseStr("abcdefg", 2));
        System.out.println(rs.reverseStr("abcdefg", 4));
        System.out.println(rs.reverseStr("ab", 4));
        System.out.println(rs.reverseStr("abdsagasdfs", 1));
        System.out.println(rs.reverseWords("My name is leasion"));
    }
}
