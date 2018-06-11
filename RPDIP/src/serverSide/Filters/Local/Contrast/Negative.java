package serverSide.Filters.Local.Contrast;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 * Class Negative
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.1
 */
public class Negative implements Filter {

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>(1);

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        String title = "Negative";
        map.add(new ImageComponent(imageComponent, title, aux));

        double pixel[] = new double[image.getRaster().getNumDataElements()];
        double Lmax = image.getColorModel().getPixelSize() / image.getRaster().getNumDataElements();

        Lmax = Math.pow(2, Lmax) - 1;

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i, j, pixel);

                for (int k = 0; k < pixel.length; k++) {
                    pixel[k] = Lmax - pixel[k];
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
