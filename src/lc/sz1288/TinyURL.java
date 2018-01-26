package lc.sz1288;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Adler32;

public class TinyURL {
    private static final String PREFIX = "http://tinyurl.com/";

    private final Map<String, String> map = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        Adler32 adler32 = new Adler32();
        adler32.update(longUrl.getBytes());
        String tiny = PREFIX + Long.toHexString(adler32.getValue());
        map.put(tiny, longUrl);
        return tiny;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}
