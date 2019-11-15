package cn.augusto.annotations;

import javax.swing.*;
import java.awt.*;

public class ButtonFrame extends JFrame {
  private static final int DEFAULT_WIDTH = 600;
  private static final int DEFAULT_HEIGHT = 400;
  private JPanel panel;
  private JButton yellowButton;
  private JButton greenButton;
  private JButton redButton;

  public ButtonFrame(){
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    panel = new JPanel();
    add(panel);

    yellowButton = new JButton("Yellow");
    greenButton = new JButton("Green");
    redButton = new JButton("Red");
    panel.add(yellowButton);
    panel.add(greenButton);
    panel.add(redButton);
    ListenerDemo.processAnnotations(this);
  }

  @ActionListenerFor(source = "yellowButton")
  public void yellowBackground(){
    panel.setBackground(Color.YELLOW);
  }

  @ActionListenerFor(source = "greenButton")
  public void greenBackground(){
    panel.setBackground(Color.GREEN);
  }

  @ActionListenerFor(source="redButton")
  public void redBackground(){
    panel.setBackground(Color.RED);
  }

  public static void main(String[] argv){
    ButtonFrame bf = new ButtonFrame();
    bf.setVisible(true);
    bf.setDefaultCloseOperation(EXIT_ON_CLOSE );
  }
}
