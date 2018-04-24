/**
 * 
 */
package convex_hull;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.TreeSet;

import convex_hull.test.Point;

/**
 * <h2>QuickHull</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
public class QuickHull {
  /**
   * <h2>PointsComparator</h2>
   * Class used as comparator for 
   * the treeSet of the convex hull.
   * 
   * @author	Cristian Abrante Dorta
   * @company	University Of La Laguna
   * @date 	24 abr. 2018
   * @version 	1.0.0
   */
  public class PointsComparator implements Comparator<Point2D> {
    @Override
    public int compare(Point2D p1, Point2D p2) {
      if (p1.equals(p2)) {
        return 0;
      }
      double firstValue = Math.atan2(p1.getY() - centerPoint.getY(), p1.getX() - centerPoint.getX());
      double secondValue = Math.atan2(p2.getY() - centerPoint.getY(), p2.getX() - centerPoint.getX());
      return -Double.compare(firstValue, secondValue);
    }
  }
  private boolean firstIteration = true;
  private Point2D centerPoint = null;
  // Points that we are evaluating
  private ArrayList<Point2D> points;
  // Points of the convex hull
  private TreeSet<Point2D> convexHull;
  // Stack for evaluating the movements.
  private Stack<StepPair> stepStack;
  // Current Line that we are evaluating.
  private Line2D currentLine;
  
  /**
   * Constructor of QuickHull
   * 
   * @param points to evaluate
   */
  public QuickHull(ArrayList<Point2D> points) {
    initializeConfiguration(points);
  }
  
  /**
   * @return number of points that we are evaluating
   */
  public int getNumberOfPoints() {
    return getPoints().size();
  }
  
  /**
   * Method that returns a certain point
   * 
   * @param index of the point 
   * @return the point
   */
  public Point2D getPoint(int index) {
    if (index >= 0 && index < getNumberOfPoints()) {
      return getPoints().get(index);
    } else {
      String error = "index must be lower than number of points and greater than zero.";
      throw new IndexOutOfBoundsException(error);
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
  public void setPoints(ArrayList<Point2D> points) {
    initializeConfiguration(points);
  }
  
  /**
   * @return the currentLine
   */
  public Line2D getCurrentLine() {
    return currentLine;
  }
  
  /**
   * @return the convexHull
   */
  public TreeSet<Point2D> getConvexHull() {
    return convexHull;
  }
  
  /**
   * Method used to compute the next iteration
   * of the quick hull algorithm
   * 
   * @return true if there is a next iteration
   * and false otherwise.
   */
  public boolean nextIteration() {
    if (firstIteration) {
      firstIteration = false;      
    }
    if (!getStepStack().isEmpty()) {
      StepPair currentStep = getStepStack().pop();
      setCurrentLine(currentStep.getLine());
      ArrayList<Point2D> currentPoints = currentStep.getPoints();
      
      if (currentPoints.isEmpty()) {
        return true;
      }
      if (currentPoints.size() == 1) { 
        Point2D p = currentPoints.get(0);
        currentPoints.remove(p);
        addPointToHull(p);
        return true;
      }
      double distance = Double.MIN_VALUE;
      int furthestPoint = -1;
      for (Point2D point : currentPoints) {
        double tmpDist = LineSide.distance(getCurrentLine(), point);
        if (tmpDist > distance) {
          distance = tmpDist;
          furthestPoint = currentPoints.indexOf(point);
        }
      }
      
      Point2D p = currentPoints.get(furthestPoint);
      currentPoints.remove(p);
      addPointToHull(p);
      
      pushStep(new StepPair(new Line2D.Double(p, getCurrentLine().getP2()), currentPoints, LineSide.LEFT));
      pushStep(new StepPair(new Line2D.Double(getCurrentLine().getP1(), p), currentPoints, LineSide.LEFT));
      return true;
    } else {
      return false;
    }
  }
  

  /**
   * @param currentLine the currentLine to set
   */
  private void setCurrentLine(Line2D currentLine) {
    if (currentLine != null) {
      this.currentLine = currentLine;      
    } else {
      throw new NullPointerException("current line can't be null");
    }
  }
  
  /**
   * Method used to initialize the current line
   */
  private void initializeCurrentLine() {
    Point2D maxPoint = getPoint(0);
    Point2D minPoint = getPoint(0);
    
    for (Point2D point : getPoints()) {
      // point has a bigger x component.
      if ((point.getX() - maxPoint.getX()) > LineSide.EPS) {
        maxPoint = point;
      }
      // point has a smaller x component.
      if ((minPoint.getX() - point.getX()) > LineSide.EPS) {
        minPoint = point;
      }
    }
    
    currentLine = new Line2D.Double(minPoint, maxPoint);
  }
  
  /**
   * @return the stepStack
   */
  private Stack<StepPair> getStepStack() {
    return stepStack;
  }

  /**
   * @param stepStack the stepStack to set
   */
  private void setStepStack(Stack<StepPair> stepStack) {
    this.stepStack = stepStack;
  }
  
  /**
   * Method used to push a step in the stack
   * 
   * @param step that we want to push
   */
  private void pushStep(StepPair step) {
    getStepStack().push(step);
  }
  
  /**
   * @return the top step of the stack
   */
  private StepPair popStep() {
    return getStepStack().pop();
  }
  
  /**
   * Method to initialize the stack
   */
  private void initializeStepStack() {
    setStepStack(new Stack<StepPair>());
    pushStep(new StepPair(new Line2D.Double(getCurrentLine().getP2(), getCurrentLine().getP1()), getPoints(), LineSide.LEFT));
    pushStep(new StepPair(new Line2D.Double(getCurrentLine().getP1(), getCurrentLine().getP2()), getPoints(), LineSide.LEFT));
  }
  
  /**
   * @param convexHull the convexHull to set
   */
  private void setConvexHull(TreeSet<Point2D> convexHull) {
    if (convexHull != null) {
      this.convexHull = convexHull;      
    } else {
      throw new NullPointerException("convex hull can't be null");
    }
  }
  
  /**
   * Method used to add a point to a hull
   * @param point we want to add
   */
  private void addPointToHull(Point2D point) {
    if (point != null) {
      getConvexHull().add(point);
    } else {
      throw new NullPointerException("point can't be null");
    }
  }
  
  /**
   * Initialize the configuration given a set of points.
   * @param points
   */
  private void initializeConfiguration(ArrayList<Point2D> points) {
    if (points != null) {
      this.points = points;
      this.centerPoint = calculateBarycenter();
      setConvexHull(new TreeSet<Point2D>(new PointsComparator()));
      initializeCurrentLine();
      initializeStepStack();
      addPointToHull(getCurrentLine().getP1());
      addPointToHull(getCurrentLine().getP2());
    } else {
      throw new NullPointerException("points can't be null");
    }
  }
  
  private Point2D calculateBarycenter() {
    double xCenter = 0.0;
    double yCenter = 0.0;
    for (Point2D point : getPoints()) {
      xCenter += point.getX();
      yCenter += point.getY();
    }
    return new Point2D.Double(xCenter / (double) getNumberOfPoints(), yCenter / (double) getNumberOfPoints());
  }
}