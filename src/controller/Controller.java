/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Timer;

import convex_hull.QuickHull;
import view.DrawableConvexHull;
import view.MainFrame;
import view.SettingsPanel;
import view.VisualPanel;

/**
 * <h2>Controller</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		23/04/2018
 * @version 1.0.0
 */
public class Controller implements ActionListener {
  
  class TimerController implements ActionListener {
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      computeNextIteration();
    }
  }
  
  private final int WIDTH = 1000;
  private final int HEIGHT = 1000;
  
  private ArrayList<Point2D> points;
  
  private QuickHull hullCalculator;
  private DrawableConvexHull drawableHull;
  
  private int delay = 1000;
  private Timer actionTimer;
  
  private SettingsPanel settingsPanel;
  private VisualPanel   visualPanel;
  private MainFrame     frame;
  
  public Controller(int numberOfPoints) {
    this.points = generateRandomPoints(numberOfPoints);
    
    hullCalculator = new QuickHull(points);
    drawableHull = new DrawableConvexHull(hullCalculator);
    
    actionTimer = new Timer(delay, new TimerController());
    
    settingsPanel = new SettingsPanel(this);
    visualPanel = new VisualPanel(drawableHull, WIDTH, HEIGHT);
    frame = new MainFrame(visualPanel, settingsPanel);
  }
  
  private ArrayList<Point2D> generateRandomPoints(int numberOfPoints) {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(200.0, 200.0));
    points.add(new Point2D.Double(300.0, 700.0));
    points.add(new Point2D.Double(400.0, 500.0));
    points.add(new Point2D.Double(600.0, 300.0));
    points.add(new Point2D.Double(600.0, 500.0));
    points.add(new Point2D.Double(800.0, 700.0));
    return points;
  }
  
  private void computeNextIteration() {
    if (hullCalculator.nextIteration()) {
      frame.repaint();
    }
  }
  
  /**
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    
    switch(e.getActionCommand()) {
    case SettingsPanel.INITIALIZE:
      actionTimer.start();
      break;
    case SettingsPanel.PAUSE:
      actionTimer.stop();
      break;
    case SettingsPanel.PLAY:
      if (!actionTimer.isRunning()) {
        actionTimer.start();
      }
      break;
    case SettingsPanel.STEP_BY_STEP:
      if (actionTimer.isRunning()) {
        actionTimer.stop();
      }
      computeNextIteration();
      break;
    }
    
    System.out.println("button clicked!");
  }
}
