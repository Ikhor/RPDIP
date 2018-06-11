package ClienteSide.GUI;

import Main.Starter;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import serverSide.Core.ImageComponent;
import serverSide.Core.remoteFilters;

public class GUI extends javax.swing.JFrame {

    private remoteFilters RMIFilter;

    private int UIMode = 0;

    /* SERIAL ID */
    private static final long serialVersionUID = -1666862630121809768L;

    /* Auxiliary Variables */
    private static final String PNG = "png";
    private static final String[] EXT = {"*.jpg, *.jpeg, *.png, *.gif", "jpg",
        "jpeg", "png", "gif"};

    private static final String DIR = new JFileChooser().getFileSystemView()
            .getDefaultDirectory().toString()
            + File.separator;

    /* Variables */
    private ImagePanel mImagePanel;
    private ImageComponent mImageComponent;
    private ArrayList<SimpleShower> mWindows;
    private javax.swing.JMenuItem jmiState;
    private Starter starter;

    private int ThreadQTD;
    private int state;

    /**
     * Main Constructor
     *
     * @param ip
     * @param port
     * @param starter
     */
    public GUI(String ip, String port, Starter starter) {
        if (UIMode == 0) {
            try {
                this.ThreadQTD = 1;
                this.state = 2;
                this.mWindows = new ArrayList<>();
                this.starter = starter;
                RMIFilter = (remoteFilters) Naming.lookup("rmi://" + ip + ":" + port + "/filters");
                initComponents();
                SimpleShower.gui = this;
                this.setVisible(true);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                JOptionPane.showMessageDialog(null, "Não foi possivel conectar ao servidor");
                starter.setVisible(true);
            }
        }
    }

    /**
     * Initialize Components
     */
    private void initComponents() {

        javax.swing.JMenuBar jMenuBar;

        javax.swing.JMenu jMenuFile;
        javax.swing.JMenu jMenuFilter;
        javax.swing.JMenu jMenuFilterC;
        javax.swing.JMenu jMenuFilterF;
        javax.swing.JMenu jMenuFilterB;
        javax.swing.JMenu jMenuWindow;
        javax.swing.JMenu jMenuProcessMode;
        javax.swing.JMenu jMenuHelp;
        javax.swing.JMenuItem jMenuFileExit;

        javax.swing.JMenu jMenuChannelSplit;
        javax.swing.JMenu jMenuLowPass;
        javax.swing.JMenu jMenuMorfologic;

        javax.swing.JMenuItem jmiAbout;
        javax.swing.JMenuItem jmiAlwaysOnTop;
        javax.swing.JMenuItem jmiCloseAll;
        javax.swing.JMenuItem jmiExit;
        javax.swing.JMenuItem jmiOpen;
        javax.swing.JMenuItem jmiSave;

        javax.swing.JMenuItem jmifLocal;
        javax.swing.JMenuItem jmifOpenMP;
        javax.swing.JMenuItem jmifThread;
        javax.swing.JMenuItem jmifQtdThread;

        javax.swing.JMenuItem jmifRGBSplit;
        javax.swing.JMenuItem jmifRGBSplitG;
        javax.swing.JMenuItem jmifNegative;
        javax.swing.JMenuItem jmifBinary;
        javax.swing.JMenuItem jmifLogAcuteness;
        javax.swing.JMenuItem jmifGammaAcuteness;
        javax.swing.JMenuItem jmifFourier;

        javax.swing.JMenuItem jmifLowMedia;
        javax.swing.JMenuItem jmifLowMedian;
        javax.swing.JMenuItem jmifHighPass;
        javax.swing.JMenuItem jmifGrayScale;
        javax.swing.JMenuItem jmifDilatacao;
        javax.swing.JMenuItem jmifErosao;

        javax.swing.JMenuItem jmifPrewitt;
        javax.swing.JMenuItem jmifRoberts;
        javax.swing.JMenuItem jmifSobel;
        javax.swing.JMenuItem jmifLaplaciano;

        mImagePanel = new ImagePanel(null);

        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu("File");
        jMenuFileExit = new javax.swing.JMenuItem("Close Client");
        jMenuProcessMode = new javax.swing.JMenu("Process Mode");
        jmiOpen = new javax.swing.JMenuItem("Open File...");
        jmiSave = new javax.swing.JMenuItem("Save");
        jmiExit = new javax.swing.JMenuItem("Exit");
        jmiState = new javax.swing.JMenuItem("Process: Single");

        jMenuFilterC = new javax.swing.JMenu("Constrast");
        jMenuFilterB = new javax.swing.JMenu("Border");
        jMenuFilterF = new javax.swing.JMenu("Frequency");
        jMenuFilter = new javax.swing.JMenu("Filters");

        jMenuChannelSplit = new javax.swing.JMenu("Channel Split");
        jmifRGBSplit = new javax.swing.JMenuItem("RGB Split");
        jmifRGBSplitG = new javax.swing.JMenuItem("RGB Split (Gray)");
        jmifNegative = new javax.swing.JMenuItem("Negative");
        jmifBinary = new javax.swing.JMenuItem("Binary");
        jmifLogAcuteness = new javax.swing.JMenuItem("Logarithmic Acuteness");
        jmifGammaAcuteness = new javax.swing.JMenuItem("Gamma Acuteness");
        jmifFourier = new javax.swing.JMenuItem("Fourier");

        jmifLocal = new javax.swing.JMenuItem("Single");
        jmifThread = new javax.swing.JMenuItem("Thread");
        jmifQtdThread = new javax.swing.JMenuItem("Set Thread");
        jmifOpenMP = new javax.swing.JMenuItem("OpenMP");

        jMenuLowPass = new javax.swing.JMenu("Low-Pass");
        jmifLowMedia = new javax.swing.JMenuItem("Media");
        jmifLowMedian = new javax.swing.JMenuItem("Median");
        jmifHighPass = new javax.swing.JMenuItem("High-Pass");
        jmifGrayScale = new javax.swing.JMenuItem("GrayScale");

        jMenuMorfologic = new javax.swing.JMenu("Morfologic");
        jmifDilatacao = new javax.swing.JMenuItem("Dilatation");
        jmifErosao = new javax.swing.JMenuItem("Erosion");

        jmifPrewitt = new javax.swing.JMenuItem("Prewitt");
        jmifSobel = new javax.swing.JMenuItem("Sobel");
        jmifLaplaciano = new javax.swing.JMenuItem("Laplaciano");
        jmifRoberts = new javax.swing.JMenuItem("Roberts cross");

        jMenuWindow = new javax.swing.JMenu("Window");
        jmiAlwaysOnTop = new javax.swing.JMenuItem("Always on top (ON/OFF)");
        jmiCloseAll = new javax.swing.JMenuItem("Close all windows");

        jMenuHelp = new javax.swing.JMenu("Help");
        jmiAbout = new javax.swing.JMenuItem("About");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 300));
        setLocationRelativeTo(null);
        setTitle("Remote Paralell Digital Image Process - RPDIP");

        mImagePanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelLayout;
        jPanelLayout = new javax.swing.GroupLayout(mImagePanel);
        mImagePanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(jPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 325, Short.MAX_VALUE));
        jPanelLayout.setVerticalGroup(jPanelLayout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));

        // FILE MENU
        jMenuFile.add(jmiOpen);
        jMenuFile.add(jmiSave);
        jMenuFile.add(jMenuFileExit);
        jMenuFile.add(new javax.swing.JPopupMenu.Separator());
        jMenuFile.add(jMenuProcessMode);
        jMenuFile.add(new javax.swing.JPopupMenu.Separator());
        jMenuFile.add(jmiExit);
        jMenuBar.add(jMenuFile);

        jMenuProcessMode.add(jmifLocal);
        jMenuProcessMode.add(jmifThread);
        jMenuProcessMode.add(jmifQtdThread);
        jMenuProcessMode.add(jmifOpenMP);

        // MORFOLOGIC MENU
        jMenuMorfologic.add(jmifDilatacao);
        jMenuMorfologic.add(jmifErosao);
        //jMenuFilter.add(jMenuMorfologic);

        // LOW PASS MENU
        jMenuLowPass.add(jmifLowMedia);
        jMenuLowPass.add(jmifLowMedian);
        jMenuFilterB.add(jMenuLowPass);

        // FILTER MENU
        jMenuChannelSplit.add(jmifRGBSplit);
        jMenuChannelSplit.add(jmifRGBSplitG);
        jMenuFilterC.add(jMenuChannelSplit);
        jMenuFilterC.add(jmifGrayScale);
        jMenuFilterC.add(new javax.swing.JPopupMenu.Separator());

        jMenuFilterC.add(jmifNegative);
        jMenuFilterC.add(jmifBinary);
        jMenuFilterC.add(jmifLogAcuteness);
        jMenuFilterC.add(jmifGammaAcuteness);

        jMenuBar.add(jMenuFilterC);
        jMenuFilter.add(jMenuFilterC);

        // LOW PASS MENU
        jMenuLowPass.add(jmifLowMedia);
        jMenuLowPass.add(jmifLowMedian);

        //FILTER BORDER
        jMenuFilterB.add(jmifHighPass);
        jMenuFilterB.add(jMenuLowPass);
        jMenuFilterB.add(new javax.swing.JPopupMenu.Separator());
        jMenuFilterB.add(jmifPrewitt);
        jMenuFilterB.add(jmifSobel);
        jMenuFilterB.add(jmifRoberts);
        jMenuFilterB.add(jmifLaplaciano);
        jMenuFilter.add(jMenuFilterB);

        //FREQUENCY
        jMenuFilterF.add(jmifFourier);
        //jMenuFilter.add(jMenuFilterF);

        jMenuBar.add(jMenuFilter);
        // WINDOW MENU
        jMenuWindow.add(jmiAlwaysOnTop);
        jMenuWindow.add(new javax.swing.JPopupMenu.Separator());
        jMenuWindow.add(jmiCloseAll);
        jMenuBar.add(jMenuWindow);

        // HELP MENU
        jMenuHelp.add(jmiAbout);
        jMenuBar.add(jMenuHelp);

        jMenuBar.add(jmiState);

        // MENU BAR
        setJMenuBar(jMenuBar);

        /*
         * ////////// shorcurts //////////
         */
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        //jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));

        //Constrast
        //jmifBinary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,0));
        //jmifGammaAcuteness.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,0));
        //jmifLogAcuteness.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,0));
        //jmifGrayScale.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.SHIFT_DOWN_MASK));
        //jmifRGBSplit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,0));
        //jmifRGBSplitG.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.SHIFT_DOWN_MASK));
        //jmifLogAcuteness.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,0));
        //jmifNegative.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,0));
        //Morfologic
        //jmifErosao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,0));
        //jmifDilatacao.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,0));
        jMenuFilterB.setMnemonic(KeyEvent.VK_B);

        //Process Mode
        jmifLocal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.SHIFT_DOWN_MASK));
        jmifThread.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK));
        jmifOpenMP.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.SHIFT_DOWN_MASK));

        //Others
        jmiAlwaysOnTop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        jmiCloseAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        jmiAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        jMenuFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));

        /*
         * ////////// ACTIONS //////////
         */
        // OPEN ACTION
        jmiOpen.addActionListener((java.awt.event.ActionEvent arg0) -> {
            loadImage();
        });

        // SAVE ACTION
        jmiSave.addActionListener((java.awt.event.ActionEvent arg0) -> {
            saveImage();
        });

        // EXIT ACTION
        jmiExit.addActionListener((java.awt.event.ActionEvent arg0) -> {
            log("Exiting...");
            System.exit(0);
        });

        jMenuFileExit.addActionListener((java.awt.event.ActionEvent arg0) -> {
            log("Closing client...");
            starter.ServerOff();
        });

        jmifQtdThread.addActionListener((java.awt.event.ActionEvent arg0) -> {
            setThread();
        });

        jmifThread.addActionListener((java.awt.event.ActionEvent arg0) -> {
            changeThread();
        });

        jmifLocal.addActionListener((java.awt.event.ActionEvent arg0) -> {
            changeSingle();
        });

        jmifOpenMP.addActionListener((java.awt.event.ActionEvent arg0) -> {
            changeJNI();
        });

        // FILTER: RGB Split R-G-B Mode
        jmifRGBSplit.addActionListener((java.awt.event.ActionEvent arg0) -> {
            rgbSplitChannel(false);
        });

        // FILTER: RGB Split Gray Mode
        jmifRGBSplitG.addActionListener((java.awt.event.ActionEvent arg0) -> {
            rgbSplitChannel(true);
        });

        // FILTER: Negative
        jmifNegative.addActionListener((java.awt.event.ActionEvent arg0) -> {
            negative();//Chamar metodo remoto
        });

        // FILTER: Binary
        jmifBinary.addActionListener((java.awt.event.ActionEvent arg0) -> {
            binary();
        });

        // FILTER: Logarithmic
        jmifLogAcuteness.addActionListener((java.awt.event.ActionEvent arg0) -> {
            logarithmic();
        });

        // FILTER: Gamma
        jmifGammaAcuteness.addActionListener((java.awt.event.ActionEvent arg0) -> {
            gamma();
        });

        // FILTER: Fourier
        jmifFourier.addActionListener((java.awt.event.ActionEvent arg0) -> {
            fourier();
        });

        // FILTER: Gray Scale
        jmifGrayScale.addActionListener((java.awt.event.ActionEvent arg0) -> {
            grayScale();
        });

        // FILTER: Dilatation
        jmifDilatacao.addActionListener((java.awt.event.ActionEvent arg0) -> {
            fourier();
        });

        // FILTER: Erosion
        jmifErosao.addActionListener((java.awt.event.ActionEvent arg0) -> {
            fourier();
        });

        // FILTER: Media
        jmifLowMedia.addActionListener((java.awt.event.ActionEvent arg0) -> {
            media();
        });

        // FILTER: Median
        jmifLowMedian.addActionListener((java.awt.event.ActionEvent arg0) -> {
            median();
        });

        // FILTER: HighPass
        jmifHighPass.addActionListener((java.awt.event.ActionEvent arg0) -> {
            HighPass();
        });

        // FILTER: Laplaciano
        jmifLaplaciano.addActionListener((java.awt.event.ActionEvent arg0) -> {
            Laplaciano();
        });

        // FILTER: RobertsCross
        jmifRoberts.addActionListener((java.awt.event.ActionEvent arg0) -> {
            RobertsCross();
        });

        // FILTER: Prewitt
        jmifPrewitt.addActionListener((java.awt.event.ActionEvent arg0) -> {
            Prewitt();
        });

        // FILTER: Sobel
        jmifSobel.addActionListener((java.awt.event.ActionEvent arg0) -> {
            Sobel();
        });

        // ALWAYS ON TOP
        jmiAlwaysOnTop.addActionListener((java.awt.event.ActionEvent arg0) -> {
            if (isAlwaysOnTop()) {
                setAlwaysOnTop(false);
            } else {
                setAlwaysOnTop(true);
            }
            log("Always on top status: " + isAlwaysOnTop());
        });

        // CLOSE ALL WINDOWS
        jmiCloseAll.addActionListener((java.awt.event.ActionEvent arg0) -> {
            CloseAll();
        });

        // ABOUT ACTION
        jmiAbout.addActionListener((java.awt.event.ActionEvent arg0) -> {
            JOptionPane.showMessageDialog(null, "Created by Ikhor - Felipe Oliveira\n UFERSA - 2015\n");
            log("About");
        });

        javax.swing.GroupLayout layout;
        layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                        mImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                        mImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();

        log("Frame loaded");
    }

    /**
     * Seeks and loads image from the directories
     */
    private void loadImage() {
        try {
            JFileChooser jChooser = new JFileChooser(DIR);
            jChooser.setDialogTitle("Open File...");
            jChooser.setFileFilter(new FileNameExtensionFilter(EXT[0], EXT));

            if (jChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

                File file = jChooser.getSelectedFile();
                if (file == null || !file.exists()) {
                    return;
                }
                setImage(new ImageComponent(file.getName(),
                        javax.imageio.ImageIO.read(file)));
                setThreadTo1();
            }

        } catch (java.io.IOException e) {
        }
    }

    /**
     * Seeks a directory and saves the image
     */
    private void saveImage() {
        saveImage(this, mImageComponent);
    }

    /**
     * Seeks a directory and saves the image
     *
     * @param component
     * @param image
     */
    public static void saveImage(java.awt.Component component, ImageComponent image) {

        if (image == null) {
            return;
        }

        JFileChooser jChooser = new JFileChooser(DIR);
        jChooser.setDialogTitle("Save");
        jChooser.setFileFilter(new FileNameExtensionFilter(EXT[0], EXT));
        jChooser.setSelectedFile(new File("IMG " + getTime() + "." + PNG));

        if (jChooser.showSaveDialog(component) == JFileChooser.APPROVE_OPTION) {
            File file = jChooser.getSelectedFile();

            int op = JOptionPane.OK_OPTION;
            if (file.exists()) {
                op = JOptionPane.showConfirmDialog(component, file.getName()
                        + " already exists.\nDo you want to overwrite it?");
            }

            if (op == JOptionPane.OK_OPTION) {
                try {
                    if (javax.imageio.ImageIO.write(image.getImage(), PNG, file)) {
                        JOptionPane.showMessageDialog(component, "Image \""
                                + file.getName() + "\"saved");
                        log("Image saved: " + file.getName());
                    }
                } catch (java.io.IOException e) {
                }
            }
        }
    }

    /**
     * Sets an image to this frame
     *
     * @param imageComponent
     */
    protected void setImage(ImageComponent imageComponent) {
        this.mImageComponent = imageComponent;
        log("Image loaded: " + imageComponent.getTitle());

        mImagePanel.setImageComponent(imageComponent);
        log("Image panel repainted");

        this.setSize(imageComponent.getSize(16, 61));
        this.setTitle(imageComponent.getName());
        this.repaint();
    }

    /**
     * RGB Split filter
     */
    private void rgbSplitChannel(boolean grayShades) {
        if (this.mImageComponent == null) {
            return;
        }

        try {
            this.showSimpleShower(RMIFilter.ChannelSplit(mImageComponent, grayShades, ThreadQTD, state));
        } catch (RemoteException ex) {
        }
        logF("RGB Split" + (grayShades ? " By Component" : ""));
    }

    private void median() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Median(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
        }

        logF("Median");
    }

    private void media() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Media(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }
        logF("Media");
    }

    private void HighPass() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.HighPass(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("High-Pass");
    }

    private void binary() {
        if (this.mImageComponent == null) {
            return;
        }

        try {
            String limiar = JOptionPane.showInputDialog(null, "Type Limiar value");
            this.showSimpleShower(RMIFilter.Binary(mImageComponent, Integer.parseInt(limiar), ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Binary");
    }

    /**
     * Negative filter
     */
    private void negative() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Negative(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Negative");
    }

    /**
     * Logarithmic filter
     */
    private void logarithmic() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Logarithmic(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Logarithmic");
    }

    /**
     * Gray Scale filter
     */
    private void grayScale() {

        if (this.mImageComponent == null) {
            return;
        }

        try {
            this.showSimpleShower(RMIFilter.GrayScale(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Gray Scale");
    }

    /**
     * Gamma filter
     */
    private void gamma() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Gamma(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Gamma");
    }

    private void fourier() {
        if (true) {
            JOptionPane.showMessageDialog(null, "Functionality in development");
            return;
        }
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Fourier(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Fourier");
    }

    private void Laplaciano() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Laplaciano(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Laplaciano");
    }

    private void RobertsCross() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Roberts(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Roberts Cross");
    }

    private void Sobel() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Sobel(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Sobel");
    }

    private void Prewitt() {
        if (this.mImageComponent == null) {
            return;
        }
        try {
            this.showSimpleShower(RMIFilter.Prewitt(mImageComponent, ThreadQTD, state));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Server Not Found!");
            starter.ServerOff();
        }

        logF("Prewitt");
    }

    /**
     * Shows the images in a simples window
     */
    private void showSimpleShower(ArrayList<ImageComponent> map) {
        if (map != null) {
            map.stream().forEach((imageComponent) -> {
                this.mWindows.add(new SimpleShower(imageComponent));
            });
        } else {
            JOptionPane.showMessageDialog(null, "Filter with problem contact the ADM!");
        }
    }

    /**
     * Log printer
     *
     * @param msg
     */
    private static void log(String msg) {
        System.out.println(getTime("HH:mm:ss") + " >> " + msg);
    }

    /**
     * Log Filter printer
     *
     * @param msg
     */
    private static void logF(String msg) {
        log("Filter applied -> " + msg);
    }

    /**
     * GET of time stamp
     */
    private static String getTime() {
        return getTime("yyyy-MM-dd HH.mm.ss");
    }

    /**
     * GET of time stamp
     */
    private static String getTime(String time) {
        return new java.text.SimpleDateFormat(time)
                .format(new java.util.Date());
    }

    /**
     * SET Process mode to Thread
     */
    private void changeThread() {
        state = 1;
        log("Thread");
        JOptionPane.showMessageDialog(null, "Process: Thread");
        jmiState.setText("Java Thread Actives:" + ThreadQTD);
    }

    /**
     * SET Process mode to OpenMP
     */
    private void changeJNI() {
        state = 0;
        log("OpenMP");
        JOptionPane.showMessageDialog(null, "Process: OpenMP");
        jmiState.setText("OpenMP Thread Actives:" + ThreadQTD);
    }

    /**
     * SET Process mode to Single
     */
    private void changeSingle() {
        state = 2;
        log("Single");
        JOptionPane.showMessageDialog(null, "Process: Single");
        jmiState.setText("Single Mode");
    }

    private void setThread() {
        if (this.mImageComponent == null) {
            JOptionPane.showMessageDialog(null, "First select an image!");
            return;
        }
        String setThread = JOptionPane.showInputDialog(null, "Type Thread pool");
        ThreadQTD = Integer.parseInt(setThread);
        if (this.mImageComponent.getSize().width < ThreadQTD) {
            ThreadQTD = this.mImageComponent.getSize().width;
        }

        logF("SetThread");
        if (state == 0) {
            changeJNI();
        } else if (state == 1) {
            changeThread();
        } else {
            changeSingle();
        }
    }

    private void setThreadTo1() {
        ThreadQTD = 1;
        logF("SetThreadTo1");
        if (state == 0) {
            changeJNI();
        } else if (state == 1) {
            changeThread();
        } else {
            changeSingle();
        }
    }

    public void CloseAll() {
        mWindows.stream().forEach((window) -> {
            window.dispose();
        });
        mWindows.clear();
        log("Close all windows");
    }

}
