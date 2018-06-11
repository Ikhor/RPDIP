package serverSide.Core;

import java.util.ArrayList;

public interface Filter {

    public abstract ArrayList<ImageComponent> apply(ImageComponent image);

    public ArrayList<Boundary> getBoundaries();

}
