import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class ImageComponent extends JLabel {

  private final float[] gaussOne = {
    0.003765f,	0.015019f,	0.023792f,	0.015019f,	0.003765f,
    0.015019f,	0.059912f,	0.094907f,	0.059912f,	0.015019f,
    0.023792f,	0.094907f,	0.150342f,	0.094907f,	0.023792f,
    0.015019f,	0.059912f,	0.094907f,	0.059912f,	0.015019f,
    0.003765f,	0.015019f,	0.023792f,	0.015019f,	0.003765f
  };

  private final float[] gaussOneFive = {
    0.015026f,	0.028569f,	0.035391f,	0.028569f,	0.015026f,
    0.028569f,	0.054318f,	0.067288f,	0.054318f,	0.028569f,
    0.035391f,	0.067288f,	0.083355f,	0.067288f,	0.035391f,
    0.028569f,	0.054318f,	0.067288f,	0.054318f,	0.028569f,
    0.015026f,	0.028569f,	0.035391f,	0.028569f,	0.015026f
  };

  private final float[] gaussTwo = {
    0.023528f,	0.033969f,	0.038393f,	0.033969f,	0.023528f,
    0.033969f,	0.049045f,	0.055432f,	0.049045f,	0.033969f,
    0.038393f,	0.055432f,	0.062651f,	0.055432f,	0.038393f,
    0.033969f,	0.049045f,	0.055432f,	0.049045f,	0.033969f,
    0.023528f,	0.033969f,	0.038393f,	0.033969f,	0.023528f
  };

  private final float[] gaussTwoFive = {
    0.028672f,	0.036333f,	0.039317f,	0.036333f,	0.028672f,
    0.036333f,	0.046042f,	0.049824f,	0.046042f,	0.036333f,
    0.039317f,	0.049824f,	0.053916f,	0.049824f,	0.039317f,
    0.036333f,	0.046042f,	0.049824f,	0.046042f,	0.036333f,
    0.028672f,	0.036333f,	0.039317f,	0.036333f,	0.028672f
  };

  private final float[] gaussThree = {
    0.031827f,	0.037541f,	0.039665f,	0.037541f,	0.031827f,
    0.037541f,	0.044281f,	0.046787f,	0.044281f,	0.037541f,
    0.039665f,	0.046787f,	0.049434f,	0.046787f,	0.039665f,
    0.037541f,	0.044281f,	0.046787f,	0.044281f,	0.037541f,
    0.031827f,	0.037541f,	0.039665f,	0.037541f,	0.031827f
  };

  private BufferedImage bim = null;
  private BufferedImage filteredBim = null;
  private boolean showFiltered = false;
  private float[] kernelChoice = null;

  private int height;
  private int width;

    /*

    Function: ImageComponent()
    Purpose: Blank constructor for the ImageComponent class

  */

  public ImageComponent(){
    super();


  }

    /*

    Function: ImageComponent(BufferedImage img)
    Purpose: Constructor of the Image component class that takes a buffered image, adds a second bufferedImage
            for usage during filtering, sets the preferred size based on the dimension of img, and then repaints
            itself to show to user on screen.

  */

  public ImageComponent(BufferedImage img) {
    super();
    bim = img;
    filteredBim = new BufferedImage(bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
    setPreferredSize(new Dimension(bim.getWidth(), bim.getHeight()));
    this.repaint();
  }

    /*

    Function: setKernel(double index)
    Purpose: Takes a double integer, determines if its one of the numbers corresponding with
            the hardcoded lists, then it'll set the list as the one to apply the filter to, otherwise
            it'll stay with the last chosen or default chosen.

  */

  public void setKernel(double index) {
    if (index == 1.0) {
      kernelChoice = gaussOne;
    } else if (index == 1.5) {
      kernelChoice = gaussOneFive;
    } else if (index == 2.0) {
      kernelChoice = gaussTwo;
    } else if (index == 2.5) {
      kernelChoice = gaussTwoFive;
    } else if (index == 3.0) {
      kernelChoice = gaussThree;
    }
  }

    /*

    Function: getKernel()
    Purpose: returns current kernel list from var kernelChoice

  */

  public float[] getKernel(){
    return kernelChoice;
  }


    /*

    Function: setImage(BufferedImage img)
    Purpose: sets whatever bufferedImage given with img and paints to the Jlabel, similar to constructor

  */

  public void setImage(BufferedImage img) {
    if (img == null) return;
    bim = img;
    filteredBim = new BufferedImage(bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
    setPreferredSize(new Dimension(bim.getWidth(), bim.getHeight()));
    showFiltered = false;
    this.repaint();
  }


    /*

    Function: revert()
    Purpose: toggles boolean relating to image swapping so the user can view the unedited image.

  */

  public void revert(){
    showFiltered = false;
    this.repaint();
  }


    /*

    Function: getImage()
    Purpose: returns the original bufferedImage that is currently in use.

  */

  public BufferedImage getImage() {
    return bim;
  }


  public BufferedImage getFilteredImg(){ return filteredBim; }

  public boolean getFiltered(){ return showFiltered; }


    /*

    Function: showImage()
    Purpose: shows original image and repaints the current ImageComponent

  */

  public void showImage() {
    if (bim == null) return;
    showFiltered = false;
    this.repaint();
  }

    /*

    Function: paintComponent(Graphics g)
    Purpose: overrides the paint component to show either the filtered BufferImage or BufferedImage

  */

  public void paintComponent(Graphics g) {
    Graphics2D big = (Graphics2D) g;
    if (showFiltered)
      big.drawImage(filteredBim, 0, 0, this);
    else
      big.drawImage(bim, 0, 0, this);
  }


    /*

    Function: BlurImage()
    Purpose: Applies a kernel chosen by the user and applies the convolution to the bufferedImage, drawing it as a
            new buffered image, and copying that bufferedImage to the filteredBim

  */

  public void BlurImage() {
    if (bim == null) return;
    Kernel kernel = new Kernel(5, 5, kernelChoice);
    ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

    // make a copy of the buffered image
    BufferedImage newbim = new BufferedImage(bim.getWidth(), bim.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D big = newbim.createGraphics();
    big.drawImage(bim, 0, 0, null);

    // apply the filter the copied image
    // result goes to a filtered copy
    cop.filter(newbim, filteredBim);

    showFiltered = true;
    this.repaint();
  }

  public void resizetoCanvas(BufferedImage img, int newW, int newH) {
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    if(showFiltered){
      filteredBim = dimg;
    }else {
      bim = dimg;
    }

    this.repaint();
  }



}
