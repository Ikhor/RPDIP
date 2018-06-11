package serverSide.Filters.Local.Contrast;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 * Class Gamma
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.0
 */
public class Gamma implements Filter {

    /* Variables */
    private final Boundary c;
    private final Boundary gamma;

    /**
     * Main Constructor
     */
    public Gamma() {
        this.c = new Boundary(-5, 5, 1);
        this.gamma = new Boundary(-5, 10, 1);
    }

    /**
     * Power Gray Scale or Gamma
     *
     * @param imageComponent
     * @return
     */
    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>(1);

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        String title = "Gamma Acuteness: C" + c + ", G" + gamma;
        map.add(new ImageComponent(imageComponent, title, aux));

        double pixel[] = new double[image.getRaster().getNumDataElements()];
        float gamma = 1.01f;
        int c = 0;
        if (gamma < 1) {
            c = (int) (256 / (Math.pow(255, gamma)));
        } else {
            c = (int) ((Math.pow(255, gamma)) / 255);
        }

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i, j, pixel);
                for (int k = 0; k < pixel.length; k++) {
                    pixel[k] = c * (Math.pow(pixel[k], gamma));
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

    public void print() {

        System.out.println(c);
        System.out.println(gamma);
    }
}
