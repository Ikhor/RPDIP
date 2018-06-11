package serverSide.Filters.Local.Contrast;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 * Class Binary Local
 *
 * @author Felipe Akrios
 * @since 26/10/2014
 *
 * @version 1.1
 */
public class Binary implements Filter {

    private final int limiar;

    public Binary(int limiar) {
        this.limiar = limiar;
    }

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>(1);

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        String title = "Binary Limiar:" + limiar;
        map.add(new ImageComponent(imageComponent, title, aux));

        double pixel[] = new double[image.getRaster().getNumDataElements()];

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i, j, pixel);

                for (int k = 0; k < pixel.length; k++) {
                    if (pixel[k] > limiar) {
                        pixel[k] = 255;
                    } else {
                        pixel[k] = 0;
                    }
                }
                aux.getRaster().setPixel(i, j, pixel);
            }
        }
        return map;
    }

    @Override
    public ArrayList<Boundary> getBoundaries() {
        // TODO Auto-generated method stub
        return null;
    }

}
