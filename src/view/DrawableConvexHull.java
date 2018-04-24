package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import convex_hull.QuickHull;

public class DrawableConvexHull {
  QuickHull hullCalculator;
  private final int POINT_RADIUS = 10;
  
  
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
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke((float) POINT_RADIUS / 2));
    g2d.setColor(Color.GREEN);
    g2d.draw(getHullCalculator().getCurrentLine());
    g2d.setColor(Color.BLACK);
    for (Point2D point : getHullCalculator().getPoints()) {
      g2d.fillOval((int) (point.getX() - POINT_RADIUS), 
                   (int) (point.getY() - POINT_RADIUS), 
                    POINT_RADIUS,
                    POINT_RADIUS);
    }
  }
}
