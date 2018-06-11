package serverSide.Filters.Local.Contrast;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 * Class Gamma
 *
 * @author Felipe Akrios
 * @since 26/10/2014
 *
 * @version 1.0
 */
public class GrayScale implements Filter {

    /**
     * Gray Scale
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
        int n = image.getRaster().getNumDataElements();

        String title = "Gray Scale";
        map.add(new ImageComponent(imageComponent, title, aux));

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] pixel = new int[n];
                image.getRaster().getPixel(i, j, pixel);
                 Color color;
                if (n == 1) {
                    color = new Color(pixel[0], pixel[0], pixel[0]);
                } else {
                    int grayScale = (int) (0.299 * pixel[0] + 0.587 * pixel[1] + 0.114 * pixel[2]);
                    color = new Color(grayScale, grayScale, grayScale);
                }
                aux.setRGB(i, j, color.getRGB());
            }
        }
        return map;
    }

    @Override
    public ArrayList<Boundary> getBoundaries() {
        return null;
    }

}
