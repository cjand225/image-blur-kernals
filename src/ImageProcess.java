import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcess {

  private static String TITLE_TEXT = "Gaussian Kernel Blur";

  private JFrame iFrame;
  private controlPanel cPanel;
  private ImageComponent iCanvas;
  private BufferedImage image;


  public ImageProcess(){
    initFrame();
  }

  private void initFrame(){
    iFrame = new JFrame(TITLE_TEXT);
    iFrame.setLayout(new BorderLayout());


    iFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(final WindowEvent e) {
        System.exit(0);
      }
    });


    initImage();
    initControlPanel();
    bindOptions();
    addComponentsToFrame();




    iFrame.setLocationRelativeTo(null);
    iFrame.setVisible(true);

  }

  private void initImage(){
    iCanvas = new ImageComponent(iFrame.getWidth(), iFrame.getHeight());
  }

  private void initControlPanel(){
    cPanel = new controlPanel();
  }

  private void addComponentsToFrame(){

    iFrame.add(iCanvas, BorderLayout.CENTER);
    iFrame.add(cPanel, BorderLayout.SOUTH);

    iFrame.setSize(new Dimension(1000, 1000));
    iCanvas.setSize(new Dimension(iFrame.getWidth() - 30, iFrame.getHeight() - 30));
    cPanel.setSize(new Dimension(iFrame.getWidth(), 100));

    iFrame.pack();

  }


  private void bindOptions(){

    cPanel.getOpenButton().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int returnVal = cPanel.getfChooser().showOpenDialog(iFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File file = cPanel.getfChooser().getSelectedFile();
          try {
            image = ImageIO.read(file);
          } catch (IOException e1) {
          }

          iCanvas.setImage(image);
          iCanvas.showImage();
        }
      }
    }
    );


    cPanel.getApplyButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        iCanvas.setKernel(cPanel.getBlurAmount());
        iCanvas.BlurImage();
        iCanvas.showImage();

      }
    });


  }



  public BufferedImage readImage(String file) {

    Image image = Toolkit.getDefaultToolkit().getImage(file);
    MediaTracker tracker = new MediaTracker(new Component() {
    });
    tracker.addImage(image, 0);
    try {
      tracker.waitForID(0);
    } catch (InterruptedException e) {

    }

    BufferedImage bim = new BufferedImage(image.getWidth(iFrame), image.getHeight(iFrame), BufferedImage.TYPE_INT_RGB);

    Graphics2D big = bim.createGraphics();
    big.drawImage(image, 0, 0, iFrame);
    return bim;
  }





  public static void main(String[] args) {

    ImageProcess myProc = new ImageProcess();

  }
}
