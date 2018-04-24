/**
 * 
 */
package convex_hull.test;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import convex_hull.QuickHull;

/**
 * <h2>QuickHullTest</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
public class QuickHullTest {
  ArrayList<Point2D> points = new ArrayList<Point2D>();
  ArrayList<Point2D> solution = new ArrayList<Point2D>();
  QuickHull hullCalculator;
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    Scanner instanceScanner = new Scanner(new FileReader("src/convex_hull/test/instances.txt"));
    while (instanceScanner.hasNextDouble()) {
      points.add(new Point2D.Double(instanceScanner.nextDouble(), instanceScanner.nextDouble()));
    }
    Scanner solutionScanner = new Scanner(new FileReader("src/convex_hull/test/solutions.txt"));
    while (solutionScanner.hasNextDouble()) {
      solution.add(new Point2D.Double(solutionScanner.nextDouble(), solutionScanner.nextDouble()));
    }
    hullCalculator = new QuickHull(points);
  }

  @Test
  public void test() {
    while (hullCalculator.nextIteration());
    int index = 0;
    boolean found = false;
    for (int i = 0; i < solution.size(); ++i) {
      found = false;
      Iterator<Point2D> pointIt = hullCalculator.getConvexHull().iterator();
      while (pointIt.hasNext() && !found) {
        if (solution.get(i).equals(pointIt.next())) {
          found = true;
        }
      }
      assertTrue(found);
    }
    assertTrue(hullCalculator.getConvexHull().size() == solution.size());
  }

}
