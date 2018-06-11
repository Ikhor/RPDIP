/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.Thread.Contrast;

import java.awt.image.BufferedImage;

/**
 *
 * @author Akrios
 */
public class ChannelSplit extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;
    private final int grayShades;
    private final int channel;

    public ChannelSplit(BufferedImage image, BufferedImage aux, int threadID, int numThread, int grayShades, int channel) {
        this.image = image;
        this.aux = aux;
        this.threadID = threadID;
        this.imgWidthThread = image.getWidth() / numThread;
        this.grayShades = grayShades;
        this.channel = channel;
    }

    @Override
    public void run() {

        double pixel[] = new double[image.getRaster().getNumDataElements()];
        double value = 0.0;

        int offSet = threadID * imgWidthThread;

        for (int i = 0; i < imgWidthThread; i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                image.getRaster().getPixel(i + offSet, j, pixel);

                value = pixel[channel];

                if (grayShades == 1) {
                    for (int k = 0; k < pixel.length; k++) {
                        pixel[k] = value;
                    }
                } else {
                    for (int k = 0; k < pixel.length; k++) {
                        pixel[k] = 0;
                        pixel[channel] = value;
                    }
                }

                aux.getRaster().setPixel(i + offSet, j, pixel);
            }
        }

    }
}
