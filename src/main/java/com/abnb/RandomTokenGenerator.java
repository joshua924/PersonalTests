package com.abnb;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomTokenGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();

    public String createToken() {
        byte[] randomBytes = new byte[64];
        RANDOM.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public static void main(String[] args) {
        RandomTokenGenerator rtg = new RandomTokenGenerator();
        for (int i = 0; i < 20; i++) {
            String token = rtg.createToken();
            System.out.println(token);
        }
    }
}
