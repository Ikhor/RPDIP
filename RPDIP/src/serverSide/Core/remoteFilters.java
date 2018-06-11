package serverSide.Core;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface remoteFilters extends Remote {

    //Contrast
    ArrayList<ImageComponent> ChannelSplit(ImageComponent mImageComponent, boolean gray, int numThread, int TypeProcess) throws RemoteException;

    ArrayList<ImageComponent> Binary(ImageComponent mImageComponent, int limiar, int numThread, int TypeProcess) throws RemoteException;

    ArrayList<ImageComponent> Gamma(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;

    ArrayList<ImageComponent> GrayScale(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;

    ArrayList<ImageComponent> Logarithmic(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;

    ArrayList<ImageComponent> Negative(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;

    //Frequency
    ArrayList<ImageComponent> Fourier(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;
    
    //Border
    ArrayList<ImageComponent> Prewitt(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;
    
    ArrayList<ImageComponent> Sobel(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;
    
    ArrayList<ImageComponent> Roberts(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;
    
    ArrayList<ImageComponent> Media(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;
    
    ArrayList<ImageComponent> Median(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;
    
    ArrayList<ImageComponent> HighPass(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;
    
    ArrayList<ImageComponent> Laplaciano(ImageComponent mImageComponent, int numThread, int TypeProcess) throws RemoteException;

}
