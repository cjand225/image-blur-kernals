import javax.swing.*;

public class controlPanel extends JPanel {


  private JSlider bSlider;
  private JButton bButton;
  private JLabel bLabel;

  private int blurAmount;


  public controlPanel(){

  }

  private void init(){
    initPanel();
    initButton();
    initSlider();
    initLabel();
    addComps();
  }


  private void initPanel(){

  }

  private void initButton(){

  }

  private void initSlider(){

  }

  private void initLabel(){

  }

  private void addComps(){

  }


  public int getBlurAmount() {
    return blurAmount;
  }

  private void setBlurAmount(int amount){
    blurAmount = amount;
  }
}
