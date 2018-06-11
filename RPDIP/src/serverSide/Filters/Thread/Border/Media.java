package serverSide.Filters.Thread.Border;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Media extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;

    public Media(BufferedImage image, BufferedImage aux, int threadID, int numThread, int tamMatriz) {
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
                int[] pixel = MediaPrivate(i+offSet, j, image);
                Color color;
                if (n == 1) {
                    color = new Color(pixel[0], pixel[0], pixel[0]);
                } else {
                    color = new Color(pixel[0], pixel[1], pixel[2]);
                }
                aux.setRGB(i+offSet, j, color.getRGB());
            }
        }
    }

    int[] MediaPrivate(int linha, int coluna, BufferedImage image) {
        try {
            int n = image.getRaster().getNumDataElements();
            int linhaAtual, colunaAtual;
            int[] pixel = new int[n];
            int[] sum = new int[n];
            for (int i = 0; i < n; i++) {
                sum[i] = 0;
            }
            for (linhaAtual = linha - 1; linhaAtual <= linha + 1; linhaAtual++) {
                for (colunaAtual = coluna - 1; colunaAtual <= coluna + 1; colunaAtual++) {
                    image.getRaster().getPixel(linhaAtual, colunaAtual, pixel);
                    for (int i = 0; i < n; i++) {
                        sum[i] += pixel[i];
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                sum[i] /= 9;
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
