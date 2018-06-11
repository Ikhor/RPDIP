package serverSide.Filters.Local.Contrast;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 * Class ChannelSpit
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.0
 */
public class ChannelSpit implements Filter {

    /* Variables */
    private boolean grayShades = false;

    /**
     * Main constructor
     *
     * @param grayShades
     */
    public ChannelSpit(boolean grayShades) {
        this.grayShades = grayShades;
    }
    
    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>();

        BufferedImage aux;
        BufferedImage image = imageComponent.getImage();

        double pixel[] = new double[image.getRaster().getNumDataElements()];
        double value = 0.0;

        final String title = "RGB Split" + (grayShades ? " (Gray)" : "");
        final String[] subtitle = {"Red", "Green", "Blue"};

        String jointtitle = "";

        for (int component = 0; component < pixel.length; component++) {
            jointtitle = title + " - " + subtitle[component];

            aux = imageComponent.newFrom();
            map.add(new ImageComponent(imageComponent, jointtitle, aux));

            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    image.getRaster().getPixel(i, j, pixel);
                    value = pixel[component];

                    if (grayShades) {
                        for (int k = 0; k < pixel.length; k++) {
                            pixel[k] = value;
                        }
                    } else {
                        for (int k = 0; k < pixel.length; k++) {
                            pixel[k] = 0;
                            pixel[component] = value;
                        }
                    }
                    aux.getRaster().setPixel(i, j, pixel);
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
