/**
 * 
 */
package convex_hull;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * <h2>StepPair</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
public class StepPair {
  private Line2D line;
  private ArrayList<Point2D> points;
  
  /**
   * Constructor of StepPair
   * 
   * @param line we are considering
   * @param side that we want to explore.
   */
  public StepPair(Line2D line, ArrayList<Point2D> points, LineSide side) {
    setLine(line);
    setPoints(points, side);
  }

  /**
   * @return the line
   */
  public Line2D getLine() {
    return line;
  }

  /**
   * @param line the line to set
   */
  public void setLine(Line2D line) {
    if (line != null) {
      this.line = line;      
    } else {
      throw new NullPointerException("line can't be null");
    }
  }

  /**
   * @return the points
   */
  public ArrayList<Point2D> getPoints() {
    return points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(ArrayList<Point2D> points, LineSide side) {
    if (points != null) {
      this.points = new ArrayList<Point2D>();
      for (Point2D point : points) {
        if (LineSide.getSide(getLine(), point) == side) {
          this.points.add(point);
        }
      }
      
    } else {
      throw new NullPointerException("points can't be null");
    }
  }
}
