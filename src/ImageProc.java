import javax.swing.*;
import java.awt.*;

public class ImageProc {

  private static String TITLE_TEXT = "Gaussian Kernel Blur";

  private JFrame iFrame;
  private controlPanel cPanel;
  private ImageCanvas iCanvas;
  private ProcImage pImage;

  private final float[] guassOne = {
    0.003765f,	0.015019f,	0.023792f,	0.015019f,	0.003765f,
    0.015019f,	0.059912f,	0.094907f,	0.059912f,	0.015019f,
    0.023792f,	0.094907f,	0.150342f,	0.094907f,	0.023792f,
    0.015019f,	0.059912f,	0.094907f,	0.059912f,	0.015019f,
    0.003765f,	0.015019f,	0.023792f,	0.015019f,	0.003765f
  };

  private final float[] guassOneFive = {

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


  public ImageProc(){

    initFrame();
    initCanvas();
    initControlPanel();
    bindOptions();
    bindListeners();
    addComponentsToFrame();

  }

  private void initFrame(){
    iFrame = new JFrame(TITLE_TEXT);
    iFrame.setLayout(new BorderLayout());
  }

  private void initCanvas(){
    iCanvas = new ImageCanvas();

  }

  private void initControlPanel(){
    cPanel = new controlPanel();
  }

  private void addComponentsToFrame(){

  }


  private void bindOptions(){

  }

  private void bindListeners(){

  }

  public static void main(String[] args) {

    ImageProc myProc = new ImageProc();

  }
}
