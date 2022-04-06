package com.abnb;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A runner to compare 2 image processing mechanisms: one that reads the image input stream header, another that
 * loads the whole image to get the dimensions. Currently the analysis show great difference between the 2,
 * especially on larger files. I would strongly recommend using header based processor in production applications.
 * In my tests, for a 3MB image file, header based processor took an average of 16 milliseconds to get dimensions,
 * while the image based processor took 2763 milliseconds on average.
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
}
