package ClienteSide.GUI;

import javax.swing.JOptionPane;
import serverSide.Core.ImageComponent;

public class SimpleShower extends javax.swing.JFrame {

    /**
     * SERIAL ID
     */
    private static final long serialVersionUID = -6399870749041720811L;

    /* Variables */
    private static java.util.Random rnd = new java.util.Random();

    protected static GUI gui;
    private ImageComponent imageComponent;

    /**
     * Main Constructor
     *
     * @param imageComponent
     */
    public SimpleShower(ImageComponent imageComponent) {
        this(imageComponent, true);
    }

    /**
     * Designated Constructor
     *
     * @param imageComponent
     * @param visible
     */
    public SimpleShower(ImageComponent imageComponent, boolean visible) {

        this.imageComponent = imageComponent;

        this.add(new ImagePanel(imageComponent));
        this.setTitle(imageComponent.getTitle());

        this.setSize(gui.getSize());

        this.setResizable(true);
        this.setLocation(randomLocation());

        this.initPopMenu();
        this.setVisible(visible);
    }

    /**
     * Initializes the pop up menu
     */
    private void initPopMenu() {
        final javax.swing.JPopupMenu jppMenu;
        javax.swing.JMenuItem jmiSave;
        javax.swing.JMenuItem jmiTransfer;
        javax.swing.JMenuItem jmiHistory;

        jppMenu = new javax.swing.JPopupMenu("Options");
        jmiSave = new javax.swing.JMenuItem("Save");
        jmiTransfer = new javax.swing.JMenuItem("Transfer");
        jmiHistory = new javax.swing.JMenuItem("History");

        jppMenu.add(new javax.swing.JSeparator());
        jppMenu.add(jmiSave);
        jppMenu.add(jmiTransfer);
        jppMenu.add(jmiHistory);
        jppMenu.add(new javax.swing.JSeparator());

        final javax.swing.JFrame frame = this;

        // SAVE ACTION
        jmiSave.addActionListener((java.awt.event.ActionEvent arg0) -> {
            GUI.saveImage(frame, imageComponent);
        });

        // TRANSFER ACTION
        jmiTransfer.addActionListener((java.awt.event.ActionEvent arg0) -> {
            gui.setImage(imageComponent);
        });

        // HISTORY ACTION
        jmiHistory.addActionListener((java.awt.event.ActionEvent arg0) -> {
            JOptionPane.showMessageDialog(frame,
                    imageComponent.getHistory());
        });

        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    jppMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

    }

    /**
     * Randomly chooses a location for the window
     *
     * @return
     */
    protected static java.awt.Point randomLocation() {
        return new java.awt.Point(rnd.nextInt(1000) + 10, rnd.nextInt(100) + 11);
    }
}
