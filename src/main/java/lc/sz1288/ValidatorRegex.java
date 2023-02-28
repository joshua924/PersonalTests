package lc.sz1288;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidatorRegex {
    public static void main(String[] args) {
        String result = extractPath();
        System.out.println(result);
    }

    private static String extractPath() {
        String s = "[recursive_violation:placeholder:[recursive_violation:damagedItemInfo:[non_null_violation:cost]]]";
        return Stream.of(s.split(":\\[")).map(
                each -> {
                    String segment = each.replaceAll("[\\[|\\]]", "");
                    String fieldName = segment.split(":")[1];
                    return fieldName.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
                }
        )
        .filter(fieldName -> !"placeholder".equals(fieldName))
        .collect(Collectors.joining("."));
    }
}
