package com.abnb;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class HeaderBasedCalculator {
    public Pair<Long, Long> getWidthHeight(String filePath) {
        try (ImageInputStream inputStream = new FileImageInputStream(new File(filePath))) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(inputStream);
            ImageReader reader = readers.next();
            try {
                reader.setInput(inputStream);
                long width = reader.getWidth(0);
                long height = reader.getHeight(0);
                return new Pair<>(width, height);
            } finally {
                reader.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String getImageType(String filePath) {
        try (ImageInputStream inputStream = new FileImageInputStream(new File(filePath))) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(inputStream);
            ImageReader reader = readers.next();
            try {
                reader.setInput(inputStream);
                return reader.getFormatName().toLowerCase();
            } finally {
                reader.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
