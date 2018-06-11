/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.JNI.Border;

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
public class Roberts implements Filter {

    private final int nThread;

    public Roberts(int nThread) {
        this.nThread = nThread;
    }

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {

        ArrayList<ImageComponent> map = new ArrayList<>();
        BufferedImage image = imageComponent.getImage();
        String title = "O: Roberts";
        map.add(new ImageComponent(imageComponent, title, image));
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
        for (int k = 0; k < n; k++) {
            ParalellProcess.Roberts(channel[k], image.getWidth(), image.getHeight(), nThread);
        }

        //return image info`s
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                for (int k = 0; k < n; k++) {
                    pixel[k] = channel[k][j * image.getWidth() + i];
                }
                image.getRaster().setPixel(i, j, pixel);
            }
        }

        return map;
    }

    @Override
    public ArrayList<Boundary> getBoundaries() {
        return null;
    }
}
