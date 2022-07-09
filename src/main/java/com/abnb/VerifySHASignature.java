package com.abnb;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class VerifySHASignature {
    private static final String SHA_256_WITH_RSA = "SHA256withRSA";
    private static final String PUBLIC_KEY = "some key";
    private static final String SIGNATURE = "redacted";

    public static void main(String[] args) throws Exception {
        String unsignedText = "123";
        Signature signatureChecker = Signature.getInstance(SHA_256_WITH_RSA);
        PublicKey publicKey = getPublicKey();
        signatureChecker.initVerify(publicKey);
        signatureChecker.update(unsignedText.getBytes(StandardCharsets.UTF_8));
        System.out.println(signatureChecker.verify(Base64.getDecoder().decode(SIGNATURE.getBytes(StandardCharsets.UTF_8))));
    }

    private static PublicKey getPublicKey() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        byte[] keyData = Base64.getDecoder().decode(PUBLIC_KEY.getBytes(StandardCharsets.UTF_8));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyData);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}
