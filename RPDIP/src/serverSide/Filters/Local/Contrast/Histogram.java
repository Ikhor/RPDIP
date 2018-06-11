/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.Local.Contrast;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 *
 * @author Akrios
 */
public class Histogram implements Filter {

    /**
     * Gray Scale
     *
     * @param imageComponent
     * @return
     */
    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>();

        BufferedImage aux;
        BufferedImage image = imageComponent.getImage();
        final String title = "Histogram Split";
        final String[] subtitle = {"Red", "Green", "Blue"};

        String jointtitle = "";

        int n = image.getRaster().getNumDataElements();

        for (int component = 0; component < n; component++) {
            jointtitle = title + " - " + subtitle[component];

            aux = imageComponent.newFrom();
            map.add(new ImageComponent(imageComponent, jointtitle, aux));

            int[] hist = new int[256];

            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    int[] pixel = new int[n];
                    image.getRaster().getPixel(i, j, pixel);
                    if (hist[pixel[component]] < 255) {
                        hist[pixel[component]] += 1;
                    }
                    Color color;
                    color = new Color(255, 255, 255);
                    aux.setRGB(pixel[component], aux.getHeight() - hist[component], color.getRGB());
                }
            }
        }
        return map;
    }

    @Override
    public ArrayList<Boundary> getBoundaries() {
        return null;
    }

}
