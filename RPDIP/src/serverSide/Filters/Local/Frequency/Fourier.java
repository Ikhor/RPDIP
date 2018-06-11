package serverSide.Filters.Local.Frequency;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import serverSide.Core.Boundary;
import serverSide.Core.Filter;
import serverSide.Core.ImageComponent;

public class Fourier implements Filter {

    public void Row(double inReal[][], double[][] outReal, double[][] outImag) {
        int N = inReal[0].length;        
        for (int i = 0; i < inReal.length; i++) {
            for (int k = 0; k < N; k++) {
                // For each output element
                for (int t = 0; t < N; t++) {
                    // For each input element
                    double angle = 2 * Math.PI / N * k * t;
                    outReal[i][k] += inReal[i][t] * Math.cos(angle);
                    outImag[i][k] -= inReal[i][t] * Math.sin(angle);
                }
            }
        }
    }

    public void Column(double inReal[][], double[][] inImag, double[][] outReal, double[][] outImag) {
        int N = inReal.length;
        for (int i = 0; i < inReal[0].length; i++) {
            for (int k = 0; k < N; k++) {
                // For each output element                
                for (int t = 0; t < N; t++) {
                    // For each input element
                    double angle = 2 * Math.PI * t * k / N;
                    outReal[k][i] += inReal[t][i] * Math.cos(angle) + inImag[t][i] * Math.sin(angle);
                    outImag[k][i] += inImag[t][i] * Math.cos(angle) - inReal[t][i] * Math.sin(angle);
                }
            }
        }
    }

    public double[][] abs(double[][] inReal, double[][] inImag) {
        double[][] abs = new double[inReal.length][inReal[0].length];
        for (int i = 0; i < inReal.length; i++) {
            for (int j = 0; j < inReal[0].length; j++) {
                double IR2 = inReal[i][j] * inReal[i][j];
                double IG2 = inImag[i][j] * inImag[i][j];
                abs[i][j] = Math.sqrt(IR2 + IG2);
            }
        }
        return abs;
    }

    @Override
    public ArrayList<ImageComponent> apply(ImageComponent imageComponent) {

        ArrayList<ImageComponent> map;
        map = new ArrayList<>(1);

        BufferedImage aux = imageComponent.newFrom();
        BufferedImage image = imageComponent.getImage();

        String title = "Fourier";
        map.add(new ImageComponent(imageComponent, title, aux));

        double pixel[] = new double[image.getRaster().getNumDataElements()];

        double[][] Red = new double[image.getWidth()][image.getHeight()];
        double[][] Green = new double[image.getWidth()][image.getHeight()];
        double[][] Blue = new double[image.getWidth()][image.getHeight()];

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.getRaster().getPixel(i, j, pixel);

                Red[i][j] = pixel[0];
                Green[i][j] = pixel[1];
                Blue[i][j] = pixel[2];

            }
        }

        double[][] outRealR = new double[image.getWidth()][image.getHeight()];
        double[][] outImagR = new double[image.getWidth()][image.getHeight()];
        Row(Red, outRealR, outImagR);

        double[][] outRealC = new double[image.getWidth()][image.getHeight()];
        double[][] outImagC = new double[image.getWidth()][image.getHeight()];
        Column(outRealR, outImagR, outRealC, outImagC);

        //Red = abs(outRealC, outImagC);
        Red = outRealR;

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                pixel[0] = Red[i][j];
                pixel[1] = Red[i][j];
                pixel[2] = Red[i][j];

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

    public static void computeDft(double[] inreal, double[] inimag, double[] outreal, double[] outimag) {
        int n = inreal.length;
        for (int k = 0; k < n; k++) {  // For each output element
            double sumreal = 0;
            double sumimag = 0;
            for (int t = 0; t < n; t++) {  // For each input element
                double angle = 2 * Math.PI * ((t * k) / n);
                sumreal += inreal[t] * Math.cos(angle) + inimag[t] * Math.sin(angle);
                sumimag += -inreal[t] * Math.sin(angle) + inimag[t] * Math.cos(angle);
            }
            outreal[k] = sumreal;
            outimag[k] = sumimag;
        }
    }
}
