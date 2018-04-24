package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.Controller;

public class SettingsPanel extends JPanel {
  
  private final int DEFAULT_WIDTH = 148;
  private final int DEFAULT_HEIGHT = 50;
  
  public static final String INITIALIZE = "initialize";
  public static final String PLAY = "play";
  public static final String PAUSE = "pause";
  public static final String STEP_BY_STEP = "next step";
  
  private JLabel      title = new JLabel("settings");
  private JButton     initialize = new JButton(INITIALIZE);
  private JButton     play = new JButton(PLAY);
  private JButton     pause = new JButton(PAUSE);
  private JButton     stepByStep = new JButton(STEP_BY_STEP);
  private JSlider     delaySlider = new JSlider(1, 1000);
  
  private ArrayList<JButton> buttons;
  
  public SettingsPanel(Controller controller) {
    setLayout(new GridLayout(1, 6));
    setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    setBackground(Color.RED);
    
    add(title);
    
    buttons = new ArrayList<JButton>();
    buttons.add(initialize);
    buttons.add(play);
    buttons.add(pause);
    buttons.add(stepByStep);
    initializeElements(controller);
    
    add(stepByStep);
    add(delaySlider);
  }
  
  private void initializeElements(Controller controller) {
    for (JButton button : buttons) {
      button.addActionListener(controller);
      add(button);
    }
  }
}
