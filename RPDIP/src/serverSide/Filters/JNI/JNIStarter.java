package serverSide.Filters.JNI;

public class JNIStarter {

    //Contrast
    public native void Negative(int[] img, int nThread);

    public native void Binary(int[] channel, int strouf, int nThread);

    public native void Logarithmic(int[] channel, int c, int nThread);

    public native void Gamma(int[] channel, int gamma, int c, int nThread);

    public native void GrayScale(int[] channelR, int[] channelG, int[] channelB, int nThread);

    //Falta --Pra baixo
    public native void ChannelSplit(int[] R, int[] G, int[] B, int width, int height, int channel);

    //Border
    public native void Roberts(int[] channel, int width, int height,int nThread);

    public native void Sobel(int[] channel, int width, int height,int nThread);

    public native void HighPass(int[] channel, int width, int height,int nThread);

    public native void Laplaciano(int[] channel, int width, int height,int nThread);

    public native void Media(int[] channel, int width, int height,int nThread);

    public native void Median(int[] channel, int width, int height,int nThread);

    public native void Prewitt(int[] channel, int width, int height,int nThread);

    static {
        System.loadLibrary("RPDIPDLL");
    }

}
