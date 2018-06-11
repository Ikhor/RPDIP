/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.Thread.Contrast;

import java.awt.image.BufferedImage;
import serverSide.Core.Boundary;

/**
 *
 * @author Akrios
 */
public class Gamma extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;
    private final Boundary c;
    private final Boundary gamma;

    public Gamma(BufferedImage image, BufferedImage aux, int threadID, int numThread, int limiar) {
        this.image = image;
        this.aux = aux;
        this.threadID = threadID;
        imgWidthThread = image.getWidth() / numThread;
        this.c = new Boundary(-5, 5, 1);
        this.gamma = new Boundary(-5, 10, 1);
    }

    @Override
    public void run() {

        int offSet = threadID * imgWidthThread;
        double pixel[] = new double[image.getRaster().getNumDataElements()];
        float gamma = 1.01f;
        int c = 0;
        if (gamma < 1) {
            c = (int) (256 / (Math.pow(255, gamma)));
        } else {
            c = (int) ((Math.pow(255, gamma)) / 255);
        }

        for (int i = 0; i < imgWidthThread; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i + offSet, j, pixel);
                for (int k = 0; k < pixel.length; k++) {
                    pixel[k] = c * (Math.pow(pixel[k], gamma));
                }
                aux.getRaster().setPixel(i + offSet, j, pixel);
            }
        }

    }
}
