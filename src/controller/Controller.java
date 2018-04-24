/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
public class Controller implements ActionListener, ChangeListener {
  
  class TimerController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      computeNextIteration();
    }
  }
  
  private final int WIDTH = 500;
  private final int HEIGHT = 500;
  
  private int numberOfPoints;
  private ArrayList<Point2D> points;
  
  private QuickHull hullCalculator;
  private DrawableConvexHull drawableHull;
  
  private boolean finished = false;
  private int delay = 1000;
  public static int MAX_DELAY = 10000;
  private Timer actionTimer;
  
  private SettingsPanel settingsPanel;
  private VisualPanel   visualPanel;
  private MainFrame     frame;
  
  public Controller(int numberOfPoints) {
    this.numberOfPoints = numberOfPoints;
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
    final Random r = new Random();
    for (int i = 0; i < numberOfPoints; ++i) {
      double x = (WIDTH) * r.nextDouble();
      double y = (HEIGHT - 50) * r.nextDouble();
      points.add(new Point2D.Double(x, y));
    }
    return points;
  }
  
  private void computeNextIteration() {
    if (hullCalculator.nextIteration()) {
      frame.repaint();
      System.out.println(hullCalculator.getConvexHull());
    } else {
      drawableHull.setDrawHull(true);
      frame.repaint();
      actionTimer.stop();
      finished = true;
    }
  }
  
  /**
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    drawableHull.setDrawPoints(false);
    switch(e.getActionCommand()) {
    case SettingsPanel.INITIALIZE:
      if (!finished) {
        if (!actionTimer.isRunning()) {
          finished = false;
          actionTimer.start();                  
        }
      } else {
        this.numberOfPoints = settingsPanel.getNumberOfPoints();
        points = generateRandomPoints(numberOfPoints);
        drawableHull.setDrawHull(false);
        drawableHull.setDrawPoints(false);
        hullCalculator.setPoints(points);
        actionTimer.start();
      }
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

  @Override
  public void stateChanged(ChangeEvent arg0) {
    actionTimer.setDelay(settingsPanel.getDelayValue());
  }
}
