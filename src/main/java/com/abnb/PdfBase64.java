package com.abnb;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

public class PdfBase64 {
    public static void main(String[] args) {
        File file = new File("src/main/resources/pdf/blank.pdf");
        try {
            byte[] bytes = IOUtils.toByteArray(Files.newInputStream(file.toPath()));
            String base64 = Base64.getEncoder().encodeToString(bytes);
            System.out.println(base64);
        } catch (Exception e) {
            System.out.println("Failed: " + e);
        }
    }
}
