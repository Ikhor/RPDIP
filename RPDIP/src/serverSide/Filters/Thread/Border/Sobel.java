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
public class Sobel extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;

    int maskX[] = {-1, -2, -1, 0, 0, 0, 1, 1, 1};
    int maskY[] = {-1, 0, 1, -2, 0, 1, -1, 0, 1};

    public Sobel(BufferedImage image, BufferedImage aux, int threadID, int numThread, int tamMatriz) {
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
                int[] pixel = SobelPrivate(i + offSet, j, image);
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
