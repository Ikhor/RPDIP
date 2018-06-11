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
public class Negative extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;

    public Negative(BufferedImage image, BufferedImage aux, int threadID, int numThread, int limiar) {
        this.image = image;
        this.aux = aux;
        this.threadID = threadID;
        imgWidthThread = image.getWidth() / numThread;
    }

    @Override
    public void run() {

        int offSet = threadID * imgWidthThread;

        double pixel[] = new double[image.getRaster().getNumDataElements()];
        double Lmax = image.getColorModel().getPixelSize() / image.getRaster().getNumDataElements();

        for (int i = 0; i < imgWidthThread; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i+offSet, j, pixel);

                for (int k = 0; k < pixel.length; k++) {
                    pixel[k] = Lmax - pixel[k];
                }

                aux.getRaster().setPixel(i+offSet, j, pixel);
            }
        }

    }
}
