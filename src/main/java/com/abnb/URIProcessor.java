package com.abnb;

import java.net.URI;

public class URIProcessor {
    public static void main(String[] args) {
        URI uri1 = URI.create("https://www.airbnb.com//7890");
        URI uri2 = URI.create("https://www.airbnb.com/");

        // normalize doesn't remove the trailing slash after the path
        System.out.println(uri1.normalize());
        System.out.println(uri2.normalize());
    }
}
