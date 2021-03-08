package com.abnb;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * A runner to compare 2 image processing mechanisms: one that reads the image input stream header, another that
 * loads the whole image to get the dimensions. Currently the analysis show great difference between the 2,
 * especially on larger files. I would strongly recommend using header based processor in production applications.
 */
public class ImageProcessingSpeedComparator {
    private static final int ITERATIONS = 3;

    private final HeaderBasedCalculator headerBasedCalculator = new HeaderBasedCalculator();
    private final ImageBasedCalculator imageBasedCalculator = new ImageBasedCalculator();

    public void compareCalculators(String filePath) {
        long headerCounter = 0;
        long imageCounter = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            long start = System.currentTimeMillis();
            headerBasedCalculator.getWidthHeight(filePath);
            headerCounter += System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            imageBasedCalculator.getWidthHeight(filePath);
            imageCounter += System.currentTimeMillis() - start;
        }
        System.out.printf("Header based on average took %d milliseconds%n", headerCounter / ITERATIONS);
        System.out.printf("Image based on average took %d milliseconds%n", imageCounter / ITERATIONS);
    }

    public static void main(String[] args) {
        ImageProcessingSpeedComparator comparator = new ImageProcessingSpeedComparator();
        comparator.compareCalculators("src/main/resources/images/image-1.jpg");
    }

    public static class HeaderBasedCalculator {
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
    }

    public static class ImageBasedCalculator {
        public Pair<Long, Long> getWidthHeight(String filePath) {
            try {
                BufferedImage bufferedImage = ImageIO.read(new File(filePath));
                long width = bufferedImage.getWidth();
                long height = bufferedImage.getHeight();
                return new Pair<>(width, height);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
