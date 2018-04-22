/**
 * 
 */
package convex_hull;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * <h2>LineSide</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
public enum LineSide {
  LEFT,
  RIGHT,
  CONTAINED;
  
  static public final double EPS = 0.00000001;
  
  /**
   * Method that returns the side
   * 
   * @param line of the points
   * @param point we want to evaluate.
   * @return the side
   */
  public static LineSide getSide(Line2D line, Point2D point) {
    Point2D A = line.getP1();
    Point2D B = line.getP2();
    double value = (B.getX() - A.getX()) * (point.getY() - A.getY())
                 - (B.getY() - A.getY()) * (point.getX() - A.getX());  
    
    // value > 0
    if (value > EPS) {
      return LEFT;
    // value == 0
    } else if (Math.abs(value) < EPS) {
      return CONTAINED;
    // value < 0;
    } else {
      return RIGHT;
    }
  }
  
  /**
   * Method that compute the distance between a line and a point
   * @param line
   * @param point
   * @return
   */
  public static double distance(Line2D line, Point2D point) {
    Point2D A = line.getP1();
    Point2D B = line.getP2();
    double ABx = B.getX() - A.getX();
    double ABy = B.getY() - A.getY();
    double num = ABx * (A.getY() - point.getY()) - ABy * (A.getX() - point.getX());
    // num < 0
    if (-num > EPS) {
      num = -num;
    }
    return num;
  }
}
