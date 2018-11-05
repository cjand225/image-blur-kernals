import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class controlPanel extends JPanel {

  private static String BBUTTON_TEXT = "Apply";
  private static String RBUTTON_TEXT = "Reset";
  private static String QBUTTON_TEXT = "Quit";
  private static String OBUTTON_TEXT = "Open a File";
  private static String SLABEL_TEXT = "Sigma Value: ";


  private JPanel buttonPanel;
  private JPanel sliderPanel;

  private JSlider bSlider;
  private JButton bButton;
  private JButton rButton;
  private JButton qButton;
  private JButton oButton;


  private JLabel sLabel;
  private JFileChooser fChooser;


  private double blurAmount = 2.0;


  public controlPanel(){
    super();
    init();
  }

  private void init(){
    initPanel();
    initButton();
    initSlider();
    initFileChooser();
    addComps();

  }


  private void initPanel(){
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(1000,100));
  }

  private void initButton(){
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 8));

    bButton = new JButton(BBUTTON_TEXT);
    rButton = new JButton(RBUTTON_TEXT);
    qButton = new JButton(QBUTTON_TEXT);
    oButton = new JButton(OBUTTON_TEXT);

    buttonPanel.setPreferredSize(new Dimension(this.getWidth(), 30));
  }

  private void initSlider(){
    sliderPanel = new JPanel();
    sliderPanel.setLayout(new GridLayout(2, 1));

    sLabel = new JLabel(SLABEL_TEXT + "2.0");
    bSlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);

    bSlider.setMajorTickSpacing(1);
    bSlider.setPaintTicks(true);

    bSlider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        handleSlider(bSlider.getValue());
      }
    });

    sliderPanel.setPreferredSize(new Dimension(this.getWidth(), 50));
  }


  public JButton getOpenButton(){
    return oButton;
  }

  private void initFileChooser(){
    fChooser = new JFileChooser();
  }

  private void addComps(){

    sliderPanel.add(sLabel);
    sliderPanel.add(bSlider);

    buttonPanel.add(qButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(0,0)));

    buttonPanel.add(oButton);


    buttonPanel.add(Box.createRigidArea(new Dimension(0,0)));
    buttonPanel.add(Box.createRigidArea(new Dimension(0,0)));
    buttonPanel.add(bButton);
    buttonPanel.add(rButton);

    add(sliderPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);


  }

  public JButton getApplyButton(){
    return bButton;
  }

  public JButton getResetButton(){
    return rButton;
  }

  public JFileChooser getfChooser(){
    return fChooser;
  }


  public double getBlurAmount() {
    System.out.print(blurAmount);
    return blurAmount;
  }

  private void setBlurAmount(double amount){
    blurAmount = amount;
  }

  private void handleSlider(int amount) {
    if(amount == 1){
      setBlurAmount(1.0);
    }else if(amount == 2){
      setBlurAmount(1.5);
    }else if(amount == 3){
      setBlurAmount(2.0);
    }else if(amount == 4){
      setBlurAmount(2.5);
    }else if(amount == 5){
      setBlurAmount(3.0);
    }
    updateLabel();
  }

  private void updateLabel(){
    sLabel.setText(SLABEL_TEXT + blurAmount);
  }




}
