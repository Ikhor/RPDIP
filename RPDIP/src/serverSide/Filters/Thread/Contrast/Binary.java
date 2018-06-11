package serverSide.Filters.Thread.Contrast;

import java.awt.image.BufferedImage;

/**
 * Class Threadfilter
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.1
 */
public class Binary extends Thread {

    private final BufferedImage image;
    private final BufferedImage aux;
    private final int threadID;
    private final int imgWidthThread;
    private final int limiar;

    public Binary(BufferedImage image, BufferedImage aux, int threadID, int numThread, int limiar) {
        this.image = image;
        this.aux = aux;
        this.threadID = threadID;
        imgWidthThread = image.getWidth() / numThread;
        this.limiar = limiar;
    }

    @Override
    public void run() {

        double pixel[] = new double[image.getRaster().getNumDataElements()];

        int offSet = threadID * imgWidthThread;

        for (int i = 0; i < imgWidthThread; i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                image.getRaster().getPixel(i + offSet, j, pixel);

                for (int k = 0; k < pixel.length; k++) {
                    if (pixel[k] > limiar) {
                        pixel[k] = 255;
                    } else {
                        pixel[k] = 0;
                    }
                }

                aux.getRaster().setPixel(i + offSet, j, pixel);
            }
        }

    }

}
