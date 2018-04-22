/**
 * 
 */
package convex_hull.test;

import static org.junit.Assert.*;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

import convex_hull.LineSide;

/**
 * <h2>LineSIdeTest</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
public class LineSIdeTest {

  @Test
  public void test() {
    Point2D p1 = new Point2D.Double(2.0, 2.0);
    Point2D p2 = new Point2D.Double(8.0, 7.0);
    Line2D line = new Line2D.Double(p1, p2);
    
    Point2D other = new Point2D.Double(6.0, 3.0);
    assertEquals(LineSide.RIGHT, LineSide.getSide(line, other));
    other = new Point2D.Double(3.0, 7.0);
    assertEquals(LineSide.LEFT, LineSide.getSide(line, other));
    other = new Point2D.Double(2.0, 2.0);
    assertEquals(LineSide.CONTAINED, LineSide.getSide(line, other));
  }
}
