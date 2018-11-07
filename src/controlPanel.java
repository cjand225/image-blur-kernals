import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controlPanel extends JPanel {

  private static String BBUTTON_TEXT = "Apply";
  private static String RBUTTON_TEXT = "Reset";
  private static String QBUTTON_TEXT = "Quit";
  private static String OBUTTON_TEXT = "Open a File";
  private static String SLIDER_LABEL_TEXT = "Sigma Value: ";


  private static int MAX_VALUE = 5;
  private static int MIN_VALUE = 1;
  private static int DEFAULT_VALUE = 3;

  private JSlider bSlider;
  private JButton aButton;
  private JButton rButton;
  private JButton qButton;
  private JButton oButton;


  private JLabel sLabel;
  private JFileChooser fChooser;
  private double blurAmount = 2.0;


  /*
  Function: controlPanel()
  Purpose: constructor used for creating the JPanel based controlPanel

 */
  public controlPanel(){
    super();
    init();
  }

  /*
    Function: init()
    Purpose: overall initalizer for everything involved on the panel.

   */
  private void init(){

    fChooser = new JFileChooser();

    initPanel();
    initButton();
    initSlider();
    addComps();

  }

  /*
    Function: initPanel()
    Purpose: settings that deal with panel setup

   */
  private void initPanel(){
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
  }

  /*
  Function: initButtons()
  Purpose: Intializing everything relating to all the buttons

 */
  private void initButton(){

    aButton = new JButton(BBUTTON_TEXT);
    rButton = new JButton(RBUTTON_TEXT);
    qButton = new JButton(QBUTTON_TEXT);
    oButton = new JButton(OBUTTON_TEXT);

    qButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
  }

  /*
  Function: initSlider()
  Purpose: intializing everything relating to the JSlider

 */
  private void initSlider(){
    sLabel = new JLabel(SLIDER_LABEL_TEXT + blurAmount);
    bSlider = new JSlider(JSlider.HORIZONTAL, MIN_VALUE, MAX_VALUE, DEFAULT_VALUE);

    bSlider.setMajorTickSpacing(1);
    bSlider.setPaintTicks(true);

    bSlider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        handleSlider(bSlider.getValue());
      }
    });

    bSlider.setPreferredSize(new Dimension(20,20));



  }

  /*
  Function: addComps()
  Purpose: adds componenets based on a specific order, allowing easier managment of moving components on gui

 */
  private void addComps(){

    add(sLabel);
    add(Box.createRigidArea(new Dimension(50,0)));
    add(bSlider);

    add(Box.createRigidArea(new Dimension(50,0)));
    add(aButton);
    add(rButton);
    add(oButton);
    add(qButton);

  }

    /*
    Function: getOpenButton()
    Purpose: gives access to the open button to bind functions from the ImageComponent Class

   */

  public JButton getOpenButton(){
    return oButton;
  }

  /*
  Function: getApplyButton()
  Purpose: gives access to the apply button to bind functions from the ImageComponent Class

 */
  public JButton getApplyButton(){
    return aButton;
  }

  /*
  Function: getResetButton()
  Purpose: gives access to the reset button to bind functions from the ImageComponent Class

 */
  public JButton getResetButton(){
    return rButton;
  }

  /*
  Function: getfChooser()
  Purpose: returns the fileChooser to get access to in the ImageProcess driver class.

 */
  public JFileChooser getfChooser(){
    return fChooser;
  }

  /*
  Function: getBlurAmount()
  Purpose: gets the value of the blur amount

 */
  public double getBlurAmount() {
    return blurAmount;
  }

  /*
  Function: setBlurAmount
  Purpose: sets the sigma value for the blurring kernels

 */
  private void setBlurAmount(double amount){
    blurAmount = amount;
  }

  /*
  Function: updateLabel()
  Purpose: updates the text based on the blurAmount

 */
  private void updateLabel(){
    sLabel.setText(SLIDER_LABEL_TEXT + blurAmount);
  }


  /*
  Function: handleSlider(int amount)
  Purpose: linked to the sliderChanged changeListener, it'll get the value of the slider
          passed to this function as the parameter. if the amount is one of these amounts
          it re-interprets that as a double, updating the label, as well as setting what the
          blur amount, which decides what sigma is used in the blurring function of ImageComponent.

 */
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




}
