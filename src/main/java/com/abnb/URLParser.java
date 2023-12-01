package com.abnb;

import java.net.MalformedURLException;
import java.net.URL;

public class URLParser {
    public static void main(String[] args) {
        String s = "https://airbnb-photos.s3.amazonaws.com/pictures/check-in-guide/CheckInGuideStep-43388195/original/sdfsdcesdsd.png?a=c&c=d&e=f&Signature=3278rgsdehkfds";
        try {
            URL url = new URL(s);
            System.out.println(url.getHost());
            System.out.println(url.getPath());
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL " + s);
        }

        s = "https://a.b.c/d/e/f";
        try {
            URL url = new URL(s);
            System.out.println(url.getHost());
            System.out.println(url.getPath());
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL " + s);
        }
    }
}
