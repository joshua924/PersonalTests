package com.abnb;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageBasedCalculator {
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
