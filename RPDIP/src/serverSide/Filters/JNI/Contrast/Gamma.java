/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.JNI.Contrast;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;
import serverSide.Filters.JNI.JNIStarter;

/**
 *
 * @author Akrios
 */
public class Gamma implements Filter {

    /* Variables */
    private final Boundary c;
    private final Boundary gamma;
    private final int nThread;

    /**
     * Main Constructor
     *
     * @param nThread
     */
    public Gamma(int nThread) {
        this.c = new Boundary(-5, 5, 1);
        this.gamma = new Boundary(-5, 10, 1);
        this.nThread = nThread;
    }

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {

        ArrayList<ImageComponent> map = new ArrayList<>();

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        String title = "O: Gamma";
        map.add(new ImageComponent(imageComponent, title, aux));

        int n = image.getRaster().getNumDataElements();

        double pixel[] = new double[n];

        int[][] channel = new int[n][image.getWidth() * image.getHeight()];

        //copy image to vector
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i, j, pixel);
                for (int k = 0; k < n; k++) {
                    channel[k][j * image.getWidth() + i] = (int) pixel[k];
                }
            }
        }

        //Call JNI
        JNIStarter ParalellProcess = new JNIStarter();
        for (int k = 0; k < n; k++) {
            ParalellProcess.Gamma(channel[k], (int) gamma.getValue(), (int) c.getValue(), nThread);
        }

        //return image info`s
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                for (int k = 0; k < n; k++) {
                    pixel[k] = channel[k][j * image.getWidth() + i];
                }
                aux.getRaster().setPixel(i, j, pixel);
            }
        }

        return map;
    }

    @Override
    public ArrayList<Boundary> getBoundaries() {
        ArrayList<Boundary> b = new ArrayList<>();
        b.add(c);
        b.add(gamma);
        return b;
    }

}
