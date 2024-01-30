package com.abnb;

import org.apache.commons.lang3.tuple.Pair;

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
            return Pair.of(width, height);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
