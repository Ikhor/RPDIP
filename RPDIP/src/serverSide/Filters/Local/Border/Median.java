/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.Local.Border;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

/**
 *
 * @author Akrios
 */
public class Median implements Filter {

    /**
     * Media
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

        String title = "Low Pass - Median";
        map.add(new ImageComponent(imageComponent, title, aux));

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] pixel = MedianPrivate(i, j, image);
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

    int[] MedianPrivate(int linha, int coluna, BufferedImage image) {
        try {
            int n = image.getRaster().getNumDataElements();
            int linhaAtual, colunaAtual;
            int[] pixel = new int[n];
            int[][] median = new int[n][9];
            int[] result = new int[n];

            for (linhaAtual = linha - 1; linhaAtual <= linha + 1; linhaAtual++) {
                for (colunaAtual = coluna - 1; colunaAtual <= coluna + 1; colunaAtual++) {
                    image.getRaster().getPixel(linhaAtual, colunaAtual, pixel);
                    for (int i = 0; i < n; i++) {
                        median[i][(colunaAtual - coluna + 1) + 3 * (linhaAtual - linha + 1)] = pixel[i];
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                Arrays.sort(median[i]);
                result[i] = median[i][4];
            }

            return result;

        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            int n = image.getRaster().getNumDataElements();
            int[] pixel = new int[n];
            image.getRaster().getPixel(linha, coluna, pixel);
            return pixel;
        }
    }
}
