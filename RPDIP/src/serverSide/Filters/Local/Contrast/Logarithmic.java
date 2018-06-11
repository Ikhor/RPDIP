package serverSide.Filters.Local.Contrast;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 * Class Logarithmic
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.0
 */
public class Logarithmic implements Filter {

    /* Variables */
    private final Boundary c;

    /**
     * Main Constructor
     */
    public Logarithmic() {
        this.c = new Boundary(-5, 5, 1);
    }

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>(1);

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        String title = "Logarithmic Acuteness ";
        map.add(new ImageComponent(imageComponent, title, aux));

        double pixel[] = new double[image.getRaster().getNumDataElements()];

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i, j, pixel);
                for (int k = 0; k < pixel.length; k++) {
                    pixel[k] = 30 * Math.log(1 + pixel[k]);
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
        return b;
    }
}
