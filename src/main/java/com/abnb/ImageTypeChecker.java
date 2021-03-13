package com.abnb;

/**
 * A runner to compare 2 image processing mechanisms: one that reads the image input stream header, another that
 * loads the whole image to get the dimensions. Currently the analysis show great difference between the 2,
 * especially on larger files. I would strongly recommend using header based processor in production applications.
 * In my tests, for a 3MB image file, header based processor took an average of 16 milliseconds to get dimensions,
 * while the image based processor took 2763 milliseconds on average.
 */
public class ImageTypeChecker {
    public static void main(String[] args) {
        HeaderBasedCalculator calculator = new HeaderBasedCalculator();
        System.out.println(calculator.getImageType("src/main/resources/images/image-1.jpg"));
        System.out.println(calculator.getImageType("src/main/resources/images/image-2.jpg"));
        System.out.println(calculator.getImageType("src/main/resources/images/image-3.png"));
        System.out.println(calculator.getImageType("src/main/resources/images/image-4.gif"));
    }
}
