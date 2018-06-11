package serverSide.Filters.Thread;

import serverSide.Filters.Thread.Border.*;
import serverSide.Filters.Thread.Contrast.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import serverSide.Core.ImageComponent;

/**
 * Class Thread Starter
 *
 * @author Felipe Akrios
 * @since 26/10/2014
 *
 * @version 1.1
 *
 *
 * MAP:
 * ---------------------------------------------------- 
 * Constrast
 * ----------------------------------------------------
 * 01 - Negative
 * 02 - Binary 
 * 03 - Logarithmic
 * 04 - Gamma
 * 05 - Channel Split
 * 06 - Gray Scale
 * ---------------------------------------------------- 
 * Border
 * ---------------------------------------------------- 
 * 07 - Media
 * 08 - Median
 * 09 - High-Pass
 * 10 - Sobel
 * 11 - Prewitt
 * 12 - Laplaciano
 * 13 - Roberts
 */
public class threadStarter {

    public ArrayList<ImageComponent> apply(ImageComponent imageComponent, int numThread, int filter, int other) {

        ArrayList<ImageComponent> map = new ArrayList<>();

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        Thread[] threadVector = null;
        String title = "";

        if (filter == 1) {
            threadVector = new Negative[numThread];
            title = "T: Negative";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Negative(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 2) {
            threadVector = new Binary[numThread];
            title = "T: Binary";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Binary(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 3) {
            threadVector = new Logarithmic[numThread];
            title = "T: Logarithmic";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Logarithmic(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 4) {
            threadVector = new Gamma[numThread];
            title = "T: Gamma";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Gamma(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 5) {
            int n = image.getRaster().getNumDataElements();
            threadVector = new ChannelSplit[numThread];
            final String[] subtitle = {"Red", "Green", "Blue"};

            for (int component = 0; component < n; component++) {
                BufferedImage auxR = imageComponent.newFrom();
                title = "T: " + subtitle[component];
                map.add(new ImageComponent(imageComponent, title, auxR));
                for (int i = 0; i < numThread; i++) {
                    threadVector[i] = new ChannelSplit(image, auxR, i, numThread, other, component);
                    threadVector[i].start();
                }
            }

        } else if (filter == 6) {
            threadVector = new GrayScale[numThread];
            title = "T: Gray Scale";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new GrayScale(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 7) {
            threadVector = new Media[numThread];
            title = "T: Low-Pass Media";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Media(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 8) {
            threadVector = new Median[numThread];
            title = "T: Low-Pass Median";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Median(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 9) {
            threadVector = new HighPass[numThread];
            title = "T: HighPass";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new HighPass(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 10) {
            threadVector = new Sobel[numThread];
            title = "T: Sobel";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Sobel(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 11) {
            threadVector = new Prewitt[numThread];
            title = "T: Prewitt";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Prewitt(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 12) {
            threadVector = new Laplaciano[numThread];
            title = "T: Laplaciano";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Laplaciano(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        } else if (filter == 13) {
            threadVector = new Roberts[numThread];
            title = "T: Roberts";
            for (int i = 0; i < numThread; i++) {
                threadVector[i] = new Roberts(image, aux, i, numThread, other);
                threadVector[i].start();
            }
        }

        try {
            for (Thread threadVector1 : threadVector) {
                threadVector1.join();
            }
        } catch (InterruptedException e) {
        }

        if (filter != 5) {
            map.add(new ImageComponent(imageComponent, title, aux));
        }
        return map;
    }
}
