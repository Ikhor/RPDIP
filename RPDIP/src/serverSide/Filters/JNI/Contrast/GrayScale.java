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
public class GrayScale implements Filter {

    private final int nThread;

    public GrayScale(int nThread) {
        this.nThread = nThread;
    }

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {

        ArrayList<ImageComponent> map = new ArrayList<>();

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        String title = "O: GrayScale";
        map.add(new ImageComponent(imageComponent, title, aux));

        int n = image.getRaster().getNumDataElements();

        int pixel[] = new int[n];

        int[][] channel = new int[n][image.getWidth() * image.getHeight()];

        //copy image to vector
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i, j, pixel);
                for (int k = 0; k < n; k++) {
                    channel[k][j * image.getWidth() + i] = pixel[k];
                }
            }
        }

        //Call JNI
        JNIStarter ParalellProcess = new JNIStarter();
        if(n == 1)
            ParalellProcess.GrayScale(channel[0],channel[0],channel[0], nThread);
        else
            ParalellProcess.GrayScale(channel[0],channel[1],channel[2], nThread);
        

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
        return null;
    }

}
