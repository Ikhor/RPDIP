/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.Thread.Contrast;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Akrios
 */
public class GrayScale extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;

    public GrayScale(BufferedImage image, BufferedImage aux, int threadID, int numThread, int limiar) {
        this.image = image;
        this.aux = aux;
        this.threadID = threadID;
        imgWidthThread = image.getWidth() / numThread;
    }

    @Override
    public void run() {

        int n = image.getRaster().getNumDataElements();
        int offSet = threadID * imgWidthThread;

        for (int i = 0; i < imgWidthThread; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] pixel = new int[n];
                image.getRaster().getPixel(i+offSet, j, pixel);
                Color color;
                if (n == 1) {
                    color = new Color(pixel[0], pixel[0], pixel[0]);
                } else {
                    int grayScale = (int) (0.299 * pixel[0] + 0.587 * pixel[1] + 0.114 * pixel[2]);
                    color = new Color(grayScale, grayScale, grayScale);
                }
                aux.setRGB(i+offSet, j, color.getRGB());
            }
        }

    }

}
