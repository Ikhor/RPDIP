package serverSide.Core;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 * Class ImageComponent
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.0
 */
public final class ImageComponent implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7227046333557257295L;
    /* Variables */
    private String name;
    private String title;
    private String history;
    transient private BufferedImage image;

    /**
     * Main Constructor
     *
     * @param name
     * @param image
     */
    public ImageComponent(String name, BufferedImage image) {
        this(name, "", image, "");
    }

    /**
     * Copy Constructor
     *
     * @param imageComponent
     * @param title
     * @param image
     */
    public ImageComponent(ImageComponent imageComponent, String title, BufferedImage image) {
        this(imageComponent.getName(), title, image, imageComponent.getHistory(title));
    }

    /**
     * Designated constructor
     *
     * @param name
     * @param title
     * @param image
     * @param history
     */
    public ImageComponent(String name, String title, BufferedImage image,
            String history) {
        this.name = name;
        this.image = image;
        this.history = history;
        this.setTitle(title);
    }

    /**
     * GET of name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * GET of title
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * SET of title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = name + (title.length() > 0 ? ": " + title : "");
    }

    /**
     * GET of image
     *
     * @return
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * SET of image
     *
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * GET of history
     *
     * @return
     */
    public String getHistory() {
        return history;
    }

    /**
     * GET of history
     */
    private String getHistory(String newEvent) {
        return (history.equals("") ? "" : history + ",\n") + newEvent;
    }

    /**
     * GET of image's size
     *
     * @return
     */
    public java.awt.Dimension getSize() {
        return new java.awt.Dimension(image.getWidth(), image.getHeight());
    }

    /**
     * GET of image's size
     *
     * @param gapX
     * @param gapY
     * @return
     */
    public java.awt.Dimension getSize(int gapX, int gapY) {
        return new java.awt.Dimension(image.getWidth() + gapX,
                image.getHeight() + gapY);
    }

    public BufferedImage newFrom() {
        return new BufferedImage(image.getColorModel(), image.getRaster()
                .createCompatibleWritableRaster(), true, null);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(image, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }
}
