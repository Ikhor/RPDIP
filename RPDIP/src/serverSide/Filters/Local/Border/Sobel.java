/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.Local.Border;

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
public class Sobel implements Filter {

    /**
     * Sobel
     *
     * @param imageComponent
     * @return
     */
    int maskX[] = {-1, -2, -1, 0, 0, 0, 1, 1, 1};
    int maskY[] = {-1, 0, 1, -2, 0, 1, -1, 0, 1};

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>();

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();
        int n = image.getRaster().getNumDataElements();

        String title = "Sobel";
        map.add(new ImageComponent(imageComponent, title, aux));

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] pixel = SobelPrivate(i, j, image);
                Color color;
                if (n == 1) {
                    color = new Color(pixel[0], pixel[0], pixel[0]);
                } else {
                    color = new Color(pixel[0], pixel[1], pixel[2]);
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

    int[] SobelPrivate(int linha, int coluna, BufferedImage image) {
        try {
            int n = image.getRaster().getNumDataElements();
            int linhaAtual, colunaAtual;
            int[] pixel = new int[n];
            int[] sum = new int[n];
            int[] sum2 = new int[n];
            int[] sum3 = new int[n];
            for (int i = 0; i < n; i++) {
                sum[i] = 0;
                sum2[i] = 0;
            }
            int j = 0;
            for (linhaAtual = linha - 1; linhaAtual <= linha + 1; linhaAtual++) {
                for (colunaAtual = coluna - 1; colunaAtual <= coluna + 1; colunaAtual++) {
                    image.getRaster().getPixel(linhaAtual, colunaAtual, pixel);
                    for (int i = 0; i < n; i++) {
                        sum[i] += pixel[i] * maskX[j];
                        sum[i] += pixel[i] * maskY[j];
                    }
                    j++;
                }
            }
            for (int i = 0; i < n; i++) {
                double sum12 = sum[i] * sum[i];
                double sum22 = sum2[i] * sum2[i];
                sum3[i] = (int) Math.sqrt(sum12 + sum22);
                if (sum3[i] > 255) {
                    sum3[i] = 255;
                }
                if (sum3[i] < 0) {
                    sum3[i] = 0;
                }
            }
            return sum3;
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            int n = image.getRaster().getNumDataElements();
            int[] pixel = new int[n];
            image.getRaster().getPixel(linha, coluna, pixel);
            return pixel;
        }
    }
}
