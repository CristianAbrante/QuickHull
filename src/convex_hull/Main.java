/**
 * 
 */
package convex_hull;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * <h2>Main</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
public class Main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    points.add(new Point2D.Double(2.0, 2.0));
    points.add(new Point2D.Double(3.0, 7.0));
    points.add(new Point2D.Double(4.0, 5.0));
    points.add(new Point2D.Double(6.0, 3.0));
    points.add(new Point2D.Double(6.0, 5.0));
    points.add(new Point2D.Double(8.0, 7.0));
    QuickHull hull = new QuickHull(points);
    
    while (hull.nextIteration() != false) {
      Line2D line = hull.getCurrentLine();
      System.out.println(String.format("l = [(%f, %f), (%f, %f)]", line.getP1().getX(), line.getP1().getY(), line.getP2().getX(),line.getP2().getY()));
      String hullStr = "h = [";
      for (Point2D point : hull.getConvexHull()) {
        hullStr += String.format("(%f, %f)", point.getX(), point.getY());
      }
      hullStr += "]\n";
      System.out.println(hullStr);
    }
    
    
  }

}
