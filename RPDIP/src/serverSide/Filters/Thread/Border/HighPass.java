/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSide.Filters.Thread.Border;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Akrios
 */
public class HighPass extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;
    int mask[] = {-1, -1, -1, -1, 8, -1, -1, -1, -1};

    public HighPass(BufferedImage image, BufferedImage aux, int threadID, int numThread, int tamMatriz) {
        this.image = image;
        this.aux = aux;
        this.threadID = threadID;
        imgWidthThread = image.getWidth() / numThread;
    }

    @Override
    public void run() {

        int n = image.getRaster().getNumDataElements();
        int offSet = threadID * imgWidthThread;

        for (int i = 0; i < imgWidthThread; i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int[] pixel = HighPrivate(i + offSet, j, image);
                Color color;
                if (n == 1) {
                    color = new Color(pixel[0], pixel[0], pixel[0]);
                } else {
                    color = new Color(pixel[0], pixel[1], pixel[2]);
                }
                aux.setRGB(i + offSet, j, color.getRGB());
            }
        }
    }

    int[] HighPrivate(int linha, int coluna, BufferedImage image) {
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
