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
public class Laplaciano implements Filter {

    /**
     * Laplaciano
     *
     * @param imageComponent
     * @return
     */
    int mask[] = {0, -1, 0, -1, 4, -1, 0, -1, 0};

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {
        ArrayList<ImageComponent> map;
        map = new ArrayList<>(1);

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();
        int n = image.getRaster().getNumDataElements();

        String title = "Laplaciano";
        map.add(new ImageComponent(imageComponent, title, aux));

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] pixel = LaplacianoPrivate(i, j, image);
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

    int[] LaplacianoPrivate(int linha, int coluna, BufferedImage image) {
        try {
            int n = image.getRaster().getNumDataElements();
            int linhaAtual, colunaAtual;
            int[] pixel = new int[n];
            int[] sum = new int[n];
            for (int i = 0; i < n; i++) {
                sum[i] = 0;
            }
            int j = 0;
            for (linhaAtual = linha - 1; linhaAtual <= linha + 1; linhaAtual++) {
                for (colunaAtual = coluna - 1; colunaAtual <= coluna + 1; colunaAtual++) {
                    image.getRaster().getPixel(linhaAtual, colunaAtual, pixel);
                    for (int i = 0; i < n; i++) {
                        sum[i] += pixel[i] * mask[j];
                    }
                    j++;
                }
            }

            for (int i = 0; i < n; i++) {
                if (sum[i] > 255) {
                    sum[i] = 255;
                }
                if (sum[i] < 0) {
                    sum[i] = 0;
                }
            }

            return sum;

        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            int n = image.getRaster().getNumDataElements();
            int[] pixel = new int[n];
            image.getRaster().getPixel(linha, coluna, pixel);
            return pixel;
        }
    }
}
