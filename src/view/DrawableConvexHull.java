package view;

import java.awt.Graphics;

import convex_hull.QuickHull;

public class DrawableConvexHull {
  QuickHull hullCalculator;

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
    
  }
}
