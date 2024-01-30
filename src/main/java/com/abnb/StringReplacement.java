package com.abnb;

import java.util.Optional;

public class StringReplacement {
    public static void main(String[] args) {
        String s = "HST (13%!)(MISSING)";
        String authority = "Ontario, Canada";
        System.out.println(s.replace("(MISSING)", Optional.of(String.format(" (%s)", authority)).orElse("")));
    }
}
