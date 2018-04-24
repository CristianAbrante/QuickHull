package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.sun.awt.SecurityWarning;

public class VisualPanel extends JPanel {
  
  DrawableConvexHull drawableHull;
  
  public VisualPanel(DrawableConvexHull drawableHull, int width, int height) {
    setDrawableHull(drawableHull);
    setSize(width, height);
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawableHull.draw(g);
  }
  
  /**
   * @return the drawableHull
   */
  public DrawableConvexHull getDrawableHull() {
    return drawableHull;
  }

  /**
   * @param drawableHull the drawableHull to set
   */
  public void setDrawableHull(DrawableConvexHull drawableHull) {
    if (drawableHull != null) {
      this.drawableHull = drawableHull;      
    } else {
      throw new NullPointerException("drawable hull can't be null");
    }
  }
}
