package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import convex_hull.QuickHull;

public class DrawableConvexHull {
  
  private QuickHull hullCalculator;
  private double pointRadius = 5.0;
  private Color pointColor = Color.BLACK;
  private Color currentLineColor = Color.GREEN;
  private Color convexHullColor = Color.BLUE;
  
  private boolean drawPoints = true;
  private boolean drawHull = false;

  public DrawableConvexHull(QuickHull hullCalculator) {
    super();
    this.hullCalculator = hullCalculator;
  }

  /**
   * @return the hullCalculator
   */
  public QuickHull getHullCalculator() {
    return hullCalculator;
  }

  /**
   * @param hullCalculator the hullCalculator to set
   */
  public void setHullCalculator(QuickHull hullCalculator) {
    if (hullCalculator != null) {
      this.hullCalculator = hullCalculator;      
    } else {
      throw new NullPointerException("hull calculator can't be null");
    }
  }
  
  /**
   * @return the pointRadius
   */
  public double getPointRadius() {
    return pointRadius;
  }

  /**
   * @param pointRadius the pointRadius to set
   */
  public void setPointRadius(double pointRadius) {
    this.pointRadius = pointRadius;
  }

  /**
   * @return the pointColor
   */
  public Color getPointColor() {
    return pointColor;
  }

  /**
   * @param pointColor the pointColor to set
   */
  public void setPointColor(Color pointColor) {
    this.pointColor = pointColor;
  }

  /**
   * @return the currentLineColor
   */
  public Color getCurrentLineColor() {
    return currentLineColor;
  }

  /**
   * @param currentLineColor the currentLineColor to set
   */
  public void setCurrentLineColor(Color currentLineColor) {
    this.currentLineColor = currentLineColor;
  }

  /**
   * @return the convexHullColor
   */
  public Color getConvexHullColor() {
    return convexHullColor;
  }

  /**
   * @param convexHullColor the convexHullColor to set
   */
  public void setConvexHullColor(Color convexHullColor) {
    this.convexHullColor = convexHullColor;
  }
  
  /**
   * @return the drawPoints
   */
  public boolean getDrawPoints() {
    return drawPoints;
  }

  /**
   * @param drawPoints the drawPoints to set
   */
  public void setDrawPoints(boolean drawPoints) {
    this.drawPoints = drawPoints;
  }

  /**
   * @return the drawHull
   */
  public boolean getDrawHull() {
    return drawHull;
  }

  /**
   * @param drawHull the drawHull to set
   */
  public void setDrawHull(boolean drawHull) {
    this.drawHull = drawHull;
  }
  
  public void drawPoints(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    drawPoints(g2d);
  }
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke((float) getPointRadius() / 2));
    if (getDrawPoints()) {
      drawPoints(g2d);
      return;
    }
    if (getDrawHull()) {
      drawPoints(g2d);
      drawConvexHull(g2d, true);
      return;
    }
    drawPoints(g2d);
    drawConvexHull(g2d, false);
    drawCurrentLine(g2d);
  }
  
  private void drawPoint(Graphics2D g, Point2D point, Color color) {
    g.setColor(color);
    g.fillOval((int) (point.getX() - getPointRadius()),
               (int) (point.getY() - getPointRadius()),
               (int) (2 * getPointRadius()), 
               (int) (2 * getPointRadius()));
    g.setColor(Color.BLACK);
  }
  
  private void drawPoints(Graphics2D g) {
    for (Point2D point : getHullCalculator().getPoints()) {
      drawPoint(g, point, getPointColor());
    }
  }
  
  private void drawCurrentLine(Graphics2D g) {
    Line2D line = getHullCalculator().getCurrentLine();
    drawPoint(g, line.getP1(), getCurrentLineColor());
    drawPoint(g, line.getP2(), getCurrentLineColor());
    g.setColor(getCurrentLineColor());
    g.draw(line);
    g.setColor(Color.BLACK);
  }
  
  private void drawConvexHull(Graphics2D g, boolean complete) {
    Iterator<Point2D> pointIt = getHullCalculator().getConvexHull().iterator();
    Point2D previousPoint = null;
    Point2D currentPoint = null;
    if (pointIt.hasNext()) {
      previousPoint = pointIt.next();
      drawPoint(g, previousPoint, getConvexHullColor());
    }
    while (pointIt.hasNext()) {
      currentPoint = pointIt.next();
      g.setColor(getConvexHullColor());
      g.drawLine((int) previousPoint.getX(), 
                 (int) previousPoint.getY(), 
                 (int) currentPoint.getX(), 
                 (int) currentPoint.getY());
      drawPoint(g, currentPoint, getConvexHullColor());
      previousPoint = currentPoint;
    }
    if (complete) {
      pointIt = getHullCalculator().getConvexHull().iterator();
      if (pointIt.hasNext()) {
        previousPoint = pointIt.next();
        g.setColor(getConvexHullColor());
        g.drawLine((int) previousPoint.getX(), 
            (int) previousPoint.getY(), 
            (int) currentPoint.getX(), 
            (int) currentPoint.getY());
      }
    }
    g.setColor(Color.BLACK);
  }
  
  private Color getRandomColor() {
    final Random r = new Random();
    return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
  }
}
