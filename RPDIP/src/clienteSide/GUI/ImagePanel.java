package ClienteSide.GUI;

import serverSide.Core.ImageComponent;

/**
 * Class ImagePanel
 *
 * @author Felipe Akrios
 * @since 25/10/2014
 *
 * @version 1.0
 */
public class ImagePanel extends javax.swing.JPanel {

    /**
     * SERIAL ID
     */
    private static final long serialVersionUID = -3063412409954906334L;

    /* Variables */
    ImageComponent imageComponent;

    /**
     * Main Constructor
     */
    public ImagePanel() {
        this(null);
    }

    /**
     * Designated Constructor BufferedImage
     *
     * @param imageComponent
     */
    public ImagePanel(ImageComponent imageComponent) {
        this.imageComponent = imageComponent;
    }

    /**
     * SET of image
     *
     * @param imageComponent
     */
    public void setImageComponent(ImageComponent imageComponent) {
        this.imageComponent = imageComponent;
        this.repaint();
    }

    @Override
    public void paint(java.awt.Graphics g) {
        if (imageComponent == null) {
            super.paint(g);
            return;
        }

        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        g2.drawImage(imageComponent.getImage(), null, 0, 0);
    }
}
