package view;

import javax.swing.JPanel;

public class VisualPanel extends JPanel {
  
  DrawableConvexHull drawableHull;
  
  public VisualPanel(DrawableConvexHull drawableHull) {
    setDrawableHull(drawableHull);
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
