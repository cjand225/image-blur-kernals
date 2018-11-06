/*
  Author: Charles Andrews
  Program: Exercise 4
  Purpose: To apply a gaussian blur with a pre-determined sigma value to an image.
  Bugs:
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcess {

  private static String TITLE_TEXT = "Gaussian Blur ";
  private static String DEFAULT_IMAGE = "src/lake.jpeg";

  private String fileName;

  private JFrame iFrame;
  private controlPanel cPanel;
  private ImageComponent iCanvas;
  private BufferedImage image;


  /*

  Function: ImageProcess()
  Purpose: Blank Constructor that calls its frame initalizer to start the program.

*/
  public ImageProcess(){
    initFrame();
  }


    /*

    Function: InitFrame()
    Purpose:  Initalizes all things relating to the frame and calls bindOptions to add listeners

  */

  private void initFrame(){
    iFrame = new JFrame(TITLE_TEXT +" - " + DEFAULT_IMAGE);
    iFrame.setLayout(new BorderLayout());

    iCanvas = new ImageComponent(readImage(DEFAULT_IMAGE));

    cPanel = new controlPanel();
    cPanel.setSize(new Dimension(iFrame.getWidth(), 100));

    //bind options before adding to frame
    bindOptions();

    //add both componenets to frame
    iFrame.add(iCanvas, BorderLayout.CENTER);
    iFrame.add(cPanel, BorderLayout.PAGE_END);

    //pack components together, show to screen
    iFrame.pack();
    iFrame.setVisible(true);

  }

    /*

    Function: bindOptions()
    Purpose: adds listeners that bind various functions to their corresponding gui elements

  */

  private void bindOptions(){

    //make sure close button works
    iFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(final WindowEvent e) {
        System.exit(0);
      }
    });

    //setup filechooser with Open File Button as Action listener
    cPanel.getOpenButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        int returnVal = cPanel.getfChooser().showOpenDialog(iFrame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = cPanel.getfChooser().getSelectedFile();

          fileName = file.getName();

          try { image = ImageIO.read(file); }
          catch (IOException e1) { }

          iCanvas.setImage(image);

          iCanvas.showImage();
          iFrame.pack();
          iFrame.setTitle(TITLE_TEXT + " - " +fileName);
        }
      }
    }
    );


    //binds Apply button with Blurring function of ImageComponent
    cPanel.getApplyButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        iCanvas.setKernel(cPanel.getBlurAmount());
        iCanvas.BlurImage();
        iCanvas.resizetoCanvas(iCanvas.getFilteredImg(), iFrame.getWidth(), iFrame.getHeight() + 35);
      }
    });

    //toggles back to original untouched image;
    cPanel.getResetButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        iCanvas.revert();
      }
    });


    iFrame.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        super.componentResized(e);
        if(iCanvas.getFiltered()) {
          iCanvas.resizetoCanvas(iCanvas.getFilteredImg(), iFrame.getWidth(), iFrame.getHeight() + 35);
        }else{
          iCanvas.resizetoCanvas(iCanvas.getImage(), iFrame.getWidth(), iFrame.getHeight() + 35);
        }

      }
    });


  }

  /*

    Function: readImage(String file)
    Purpose: Takes a filepath in the form a of string, opens a given image file, and attempts to return
            that image as a bufferdImage

  */
  public BufferedImage readImage(String file) {

    Image image = Toolkit.getDefaultToolkit().getImage(file);
    MediaTracker tracker = new MediaTracker(new Component() {});

    tracker.addImage(image, 0);

    try { tracker.waitForID(0); }
    catch (InterruptedException e) { }

    BufferedImage bim = new BufferedImage(image.getWidth(iFrame), image.getHeight(iFrame), BufferedImage.TYPE_INT_RGB);

    Graphics2D big = bim.createGraphics();
    big.drawImage(image, 0, 0, iFrame);
    return bim;
  }


  public static void main(String[] args) {
    ImageProcess myProc = new ImageProcess();
  }
}
