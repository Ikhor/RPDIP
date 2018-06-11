package serverSide.Core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerMain extends UnicastRemoteObject implements remoteFilters {

    private static final long serialVersionUID = 7326701180867486767L;
    

    public ServerMain() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<ImageComponent> Negative(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Contrast.Negative(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 1, 0);
        } else {
            return new serverSide.Filters.Local.Contrast.Negative().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Binary(ImageComponent mImageComponent, int limiar, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Contrast.Binary(limiar, numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 2, limiar);
        } else {
            return new serverSide.Filters.Local.Contrast.Binary(limiar).apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Logarithmic(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Contrast.Logarithmic(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 3, 0);
        } else {
            return new serverSide.Filters.Local.Contrast.Logarithmic().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Gamma(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Contrast.Gamma(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 4, 0);
        } else {
            return new serverSide.Filters.Local.Contrast.Gamma().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> ChannelSplit(ImageComponent mImageComponent, boolean gray, int numThread, int TypeProcess) throws RemoteException {

        if (TypeProcess == 0) {
            return null;
        } else if (TypeProcess == 1) {
            if (gray) {
                return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 5, 1);
            }
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 5, 0);
        } else {
            return new serverSide.Filters.Local.Contrast.ChannelSpit(gray).apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> GrayScale(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Contrast.GrayScale(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 6, 0);
        } else {
            return new serverSide.Filters.Local.Contrast.GrayScale().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Media(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Border.Media(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 7, 0);
        } else {
            return new serverSide.Filters.Local.Border.Median().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Median(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Border.Median(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 8, 0);
        } else {
            return new serverSide.Filters.Local.Border.Median().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> HighPass(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Border.HighPass(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 9, 0);
        } else {
            return new serverSide.Filters.Local.Border.HighPass().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Sobel(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Border.Sobel(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 10, 0);
        } else {
            return new serverSide.Filters.Local.Border.Sobel().apply(mImageComponent);
        }
    }

    //Border
    @Override
    public ArrayList<ImageComponent> Prewitt(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Border.Prewitt(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 11, 0);
        } else {
            return new serverSide.Filters.Local.Border.Prewitt().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Laplaciano(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return null;
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 12, 0);
        } else {
            return new serverSide.Filters.Local.Border.Laplaciano().apply(mImageComponent);
        }
    }

    @Override
    public ArrayList<ImageComponent> Roberts(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return new serverSide.Filters.JNI.Border.Roberts(numThread).apply(mImageComponent);
        } else if (TypeProcess == 1) {
            return new serverSide.Filters.Thread.threadStarter().apply(mImageComponent, numThread, 13, 0);
        } else {
            return new serverSide.Filters.Local.Border.Roberts().apply(mImageComponent);
        }
    }


    //Frequency
    @Override
    public ArrayList<ImageComponent> Fourier(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException {
        if (TypeProcess == 0) {
            return null;
        } else {
            return new serverSide.Filters.Local.Frequency.Fourier().apply(mImageComponent);
        }
    }

}
